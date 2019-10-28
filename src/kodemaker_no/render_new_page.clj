(ns kodemaker-no.render-new-page
  (:require [clojure.java.io :as io]
            [dumdom.string :as dumdom]
            [optimus.link :as link]
            [ui.layout :as layout]
            [ui.sections.banner-section :as banner-section]
            [ui.sections.bruce-section :as bruce-section]
            [ui.sections.pønt-section :as pønt-section]
            [ui.sections.seymour-section :as seymour-section]
            [ui.sections.vertigo-section :as vertigo-section]))

(defn render-section [section]
  ((case (:kind section)
     :banner banner-section/render
     :bruce bruce-section/render
     :footer layout/footer
     :pønt pønt-section/render
     :seymour seymour-section/render
     :vertigo vertigo-section/render)
   section))

(defn- head-title [title]
  (if-let [title-str (or (:head title) (and (string? title) title))]
    (str title-str " | Kodemaker")
    "Kodemaker"))

(defn render-page [page request]
  (str "<!DOCTYPE html>"
   (dumdom/render
    [:html
     [:head
      [:link {:rel "stylesheet" :href (link/file-path request "/css/kodemaker.css")}]
      [:link {:href (link/file-path request "/favicon.ico") :rel "icon" :type "image/x-icon"}]
      [:link {:href (link/file-path request "/favicon.ico") :rel "shortcut icon" :type "image/ico"}]
      [:link {:href (link/file-path request "/favicon.ico") :rel "shortcut icon" :type "image/x-icon"}]
      [:link {:href (link/file-path request "/favicon.ico") :rel "shortcut icon" :type "image/vnd.microsoft.icon"}]
      [:title (head-title (:title page))]]
     [:body
      [:script (slurp (io/resource "public/scripts/analytics.js"))]
      (map render-section (:sections page))]])))
