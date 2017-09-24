(ns cointechcap.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [day8.re-frame.http-fx]
            [re-frisk.core :refer [enable-re-frisk!]]
            [cointechcap.events]
            [cointechcap.subs]
            [cointechcap.views :as views]
            [cointechcap.config :as config]))

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (enable-re-frisk!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root)
  (re-frame/dispatch [:fetch-graphql-githab-data]))
