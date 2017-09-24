(ns cointechcap.views
  (:require [re-frame.core :as re-frame]
            [re-com.core :refer [h-box v-box checkbox gap scroller p popover-anchor-wrapper hyperlink
                                 popover-content-wrapper]]
            [re-frame-datatable.core :as dt]
            [reagent.core :as r]))

(defn check-propietary
  [{:keys [org user]}]
  [(when-not (or org user)
     "danger")])

(defn link [href body]
  [:a {:href href :target "_blank"}
   body])

(defn icon-link [href icon]
  (link href [:span {:class icon}]))

(def social-icons
  {:slack    "icon-slack"
   :facebook "icon-facebook-squared"
   :twitter  "icon-twitter-squared"
   :reddit   "icon-reddit-alien"
   :medium   "icon-medium"
   :linkedin "icon-linkedin-squared"
   :youtube  "icon-youtube-play"
   :gplus    "icon-gplus-squared"})

(defn name-formatter [cname {:keys [org user coin? description web paper social]}]
  (let [showing?  (r/atom false)
        account (or org user)]
    [popover-anchor-wrapper
     :showing? showing?
     :position :right-center
     :anchor   [:span [hyperlink
                       :label     cname
                       :on-click  #(swap! showing? not)]
                      " "
                      (when coin?
                        [:span {:class "icon-bitcoin"}])]
     :popover  [popover-content-wrapper
                :width    "350px"
                :title    cname
                :on-cancel #(reset! showing? false)
                :body     [v-box
                           :gap "15px"
                           :children [[:span description]
                                      (when paper
                                        [:span
                                         [:span {:class "icon-doc-text"}]
                                         (for [[lng href] paper]
                                           [link href (str (name lng) " ")])])
                                      [:span
                                       (when web
                                         [icon-link web "icon-window-maximize"])
                                       " "
                                       (when account
                                         [icon-link (str "https://github.com/" account) "icon-github"])
                                       " "
                                       (when social
                                         (for [[sn href] social]
                                           (when-let [icon (get social-icons sn)]
                                             [icon-link href icon])))]]]]]))

(defn platform-formatter [platform]
  [:span {:class "label label-default"}
   (case (:type platform)
     :eth "Ethereum "
     :omni "Omni"
     "")
   (when (:erc20? platform)
     [:span {:class "label label-primary"} "ERC20"])])

(defn fork-formatter [fork]
  [:span {:class "label label-info"}
   (case fork
     :eth "Ethereum"
     :btc "Bitcoin"
     "")])

(defn aggregation-row []
  [:tr
   [:th {:col-span 3} ""]
   [:th {:col-span 7} "Github"]])

(defn coins-datatable []
  [dt/datatable
   :coins-datatable-data
   [:coins-data]
   [{::dt/column-key [:name] ::dt/column-label "Name" ::dt/render-fn name-formatter}
    {::dt/column-key [:fork] ::dt/column-label "Fork" ::dt/render-fn fork-formatter}
    {::dt/column-key [:platform] ::dt/column-label "Platform" ::dt/render-fn platform-formatter}
    {::dt/column-key [:members] ::dt/column-label "Members" ::dt/sorting {::dt/enabled? true}}
    {::dt/column-key [:repos] ::dt/column-label "Repos" ::dt/sorting {::dt/enabled? true}}
    {::dt/column-key [:open-issues] ::dt/column-label "Open issues" ::dt/sorting {::dt/enabled? true}}
    {::dt/column-key [:stars] ::dt/column-label "Stars" ::dt/sorting {::dt/enabled? true}}
    ;{::dt/column-key [:contributors] ::dt/column-label "Contributors" ::dt/sorting {::dt/enabled? true}}
    ;{::dt/column-key [:commits] ::dt/column-label "Commits" ::dt/sorting {::dt/enabled? true}}
    {::dt/column-key [:forks] ::dt/column-label "Forks" ::dt/sorting {::dt/enabled? true}}]
   {::dt/table-classes              ["ui" "celled" "stripped" "table"]
    ::dt/tr-class-fn                check-propietary
    ::dt/extra-header-row-component aggregation-row}])

(defn controls []
  (let [checkbox-model (r/atom nil)]
    (fn []
      [checkbox
       :model checkbox-model
       :on-change #(reset! checkbox-model %)
       :label "Coins"])))

(defn main-panel []
  (fn []
    [v-box
     :size "1"
     :children [[controls]
                [coins-datatable]]]))
