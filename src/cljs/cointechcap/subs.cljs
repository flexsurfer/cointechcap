(ns cointechcap.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
  :name
  (fn [db]
    (:name db)))

(re-frame/reg-sub
  :coins-data
  (fn [{:keys [coins]}]
    (mapv (fn [{:keys [api-result] :as coin}]
            (if api-result
              (let [{:keys [login members repositories]} api-result
                    {:keys [nodes]} repositories]
                (assoc coin
                  :members      (:totalCount members)
                  :repos        (:totalCount repositories)
                  :stars        (reduce + (map #(get-in % [:stargazers :totalCount]) nodes))
                  :forks        (reduce + (map #(get-in % [:forks :totalCount]) nodes))
                  :open-issues  (reduce + (map #(get-in % [:issues :totalCount]) nodes))))
              coin))
                ;:contributors (reduce + (map count contributors))
                ;:commits      (reduce + (map #(reduce + (map :total %)) contributors)))))
          (vals coins))))

