(ns ^:figwheel-hooks ui.devcards
  (:require [ui.color-cards]
            [ui.elements-cards]
            [ui.layout-cards]
            [ui.sections.seymour-section-cards]
            [ui.typography-cards]
            [devcards.core :as devcards]))

(enable-console-print!)

(defn render []
  (devcards/start-devcard-ui!))

(defn ^:after-load render-on-relaod []
  (render))

(render)
