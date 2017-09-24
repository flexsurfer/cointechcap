(ns cointechcap.events
  (:require [re-frame.core :as re-frame]
            [cointechcap.db :as db]
            [ajax.core :as ajax]
            [cointechcap.data :as data]
            [venia.core :as v]
            [clojure.string :as str]))

(def ^:const github-api "https://api.github.com")
(def ^:const github-api-v4 "https://api.github.com/graphql")

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   (assoc db/default-db :coins (into {} (map #(hash-map (:name %) (assoc % :contributors [])) data/coins)))))

(re-frame/reg-event-fx
  :fetch-githab-data
  (fn [{{:keys [coins]} :db} _]
    {:dispatch [:fetch-githab-members]
     :dispatch-n (remove nil? (map #(cond (:org %)
                                          (vector :fetch-githab-org-repos %)
                                          (:user %)
                                          (vector :fetch-githab-user-repos %))
                                   (vals coins)))}))

(re-frame/reg-event-db
  :fetch-graphql-githab-data-result
  (fn [db [_ indx-coins {:keys [data]}]]
    (let [result-names (into {} (map (fn [[idx coin]] {(:name coin) (keyword (str "item" idx))}) indx-coins))]
      (update db :coins #(into {} (map (fn [coin] {(:name coin) (assoc coin :api-result (get data (get result-names (:name coin))))})) (vals %))))))

(re-frame/reg-event-fx
  :fetch-graphql-githab-data
  (fn [{{:keys [coins] :as db} :db} _]
    (let [indx-coins (map-indexed vector (vals coins))]
      {:http-xhrio {:method          :post
                    :uri             github-api-v4
                    :headers         {"Authorization" ""}
                    :params          {:query (v/graphql-query
                                               {:venia/queries
                                                (concat
                                                  [[:rateLimit
                                                    [:limit :cost :remaining :resetAt]]]
                                                  (for [[idx coin] indx-coins
                                                        :when (:org coin)]
                                                    {:query/data
                                                     [:organization {:login (:org coin)}
                                                      [:login
                                                       [:members {:first 100}
                                                        [:totalCount]]
                                                       [:repositories {:first 100
                                                                       :orderBy {:field :STARGAZERS
                                                                                 :direction :DESC}}
                                                        [:totalCount
                                                         [:nodes
                                                          [:name :isFork
                                                           [:defaultBranchRef
                                                            [:fragment/target]]
                                                           [:forks
                                                            [:totalCount]]
                                                           [:issues {:states :OPEN}
                                                            [:totalCount]]
                                                           [:primaryLanguage
                                                            [:name]]
                                                           [:stargazers
                                                            [:totalCount]]]]]]]]
                                                     :venia/fragments [{:fragment/name   "target"
                                                                        :fragment/type   :Commit
                                                                        :fragment/fields [:history {:first 100}
                                                                                          [:edges
                                                                                           [:node
                                                                                            [:committedDate]]]]}]
                                                     :query/alias (keyword (str "item" idx))}))})}
                    :timeout         8000
                    :format          (ajax/json-request-format)
                    :response-format (ajax/json-response-format {:keywords? true})
                    :on-success      [:fetch-graphql-githab-data-result indx-coins]}})))
                    ;:on-failure      [:fetch-githab-data-fault]}}))

(re-frame/reg-event-fx
  :fetch-githab-members
  (fn [{{:keys [coins]} :db} _]
    {:dispatch-n (remove nil? (map #(when (:org %)
                                          (vector :fetch-githab-org-members %))
                                   (vals coins)))}))

(re-frame/reg-event-fx
  :request-githab-data
  (fn [_ [_ data url result]]
    {:http-xhrio {:method          :get
                  :uri             url
                  :timeout         8000
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success      [result data]
                  :on-failure      [:fetch-githab-data-fault]}}))

(re-frame/reg-event-fx
  :fetch-githab-org-repos
  (fn [_ [_ {:keys [org name]}]]
    {:dispatch [:request-githab-data
                name
                (str github-api "/orgs/" org "/repos?per_page=100")
                :fetch-githab-org-repos-result]}))

(re-frame/reg-event-fx
  :fetch-githab-org-repos-result
  (fn [{db :db} [_ name result]]
    (let [sorted (sort-by :stargazers_count > result)]
      {:db (assoc-in db [:coins name :api-result] sorted)
       :dispatch (first (mapv #(vector :fetch-githab-repo name (:name %))
                              sorted))})))

(re-frame/reg-event-fx
  :fetch-githab-user-repos
  (fn [_ [_ {:keys [user name]}]]
    {:dispatch [:request-githab-data
                name
                (str github-api "/users/" user "/repos?per_page=100")
                :fetch-githab-user-repos-result]}))

(re-frame/reg-event-db
  :fetch-githab-user-repos-result
  (fn [db [_ name result]]
    (-> db
      (assoc-in [:coins name :api-result] result)
      (assoc-in [:coins name :members] [{}]))))

(re-frame/reg-event-fx
  :fetch-githab-org-members
  (fn [_ [_ {:keys [org name]}]]
    {:dispatch [:request-githab-data
                name
                (str github-api "/orgs/" org "/members?per_page=100")
                :fetch-githab-org-members-result]}))

(re-frame/reg-event-db
  :fetch-githab-org-members-result
  (fn [db [_ name result]]
    (assoc-in db [:coins name :members] result)))

(re-frame/reg-event-fx
  :fetch-githab-repo
  (fn [{db :db} [_ name repo-name]]
    {:dispatch [:request-githab-data
                name
                (str github-api "/repos/" (get-in db [:coins name :org]) "/" repo-name "/stats/contributors?per_page=100")
                :fetch-githab-repo-result]}))

(re-frame/reg-event-db
  :fetch-githab-repo-result
  (fn [db [_ name result]]
    (update-in db [:coins name :contributors] conj result)))

