(ns kodemaker-no.new-pages.whoami-page
  (:require [clojure.java.io :as io]
            [ui.layout :as l]))

(defn render-section [_]
  [:div.section {:style (l/stylish {:height "100vh"} {:background :chablis})}
   [:div {:style {:position "absolute" :left 0 :right 0 :top 0}}
    (l/header-section {})]
   [:div.content
    [:div#illustration
     (slurp (io/resource "whoami.svg"))]]])

(defn create-page [_]
  {:title "Hvem er kodemakerne?"
   :sections [{:kind :whoami}]})
