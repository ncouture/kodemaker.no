(ns kodemaker-no.pages.project-pages-test
  (:require [kodemaker-no.pages.project-pages :refer :all]
            [kodemaker-no.homeless :refer [hiccup-find]]
            [midje.sweet :refer :all]
            [hiccup.core :refer [html]]))

(def finn-oppdrag
  {:id :finn-oppdrag
   :url "/finn-oppdrag/"
   :name "FINN oppdrag"
   :logo "/logos/finn.png"
   :description "FINN.no besluttet i 2009."
   :illustration "/photos/references/finn-oppdrag.jpg"})

(defn page [& {:as extras}]
  (((project-pages [(merge finn-oppdrag extras)]) "/finn-oppdrag/")))

(fact (-> (page) :title) => "FINN oppdrag")
(fact (-> (page) :illustration) => "/logos/finn.png")
(fact (-> (page) :lead) => [:p "FINN.no besluttet i 2009."])

(fact (->> (page :people [{:url "/magnar/"
                           :first-name "Magnar"
                           :full-name "Magnar Sveen"
                           :thumb "/photos/people/magnar/side-profile.jpg"
                           :description "Gjorde bra ting."
                           :years [2008 2009]}])
           :body html)

      => (html [:h2 "Våre folk på saken"]
               [:div.media
                [:a.img.thumb.mts {:href "/magnar/"}
                 [:img {:src "/photos/people/magnar/side-profile.jpg"}]]
                [:div.bd
                 [:h4.mtn "Magnar Sveen "
                  [:span.tiny.shy "2008-2009"]]
                 [:p "Gjorde bra ting."]]]))

(fact
 "Folk på prosjektet listes opp etter hvor mange år de har vært der,
  og hvem som startet først."
 (->> (page :people [{:full-name "Magnar" :years [2011]}
                     {:full-name "Christian" :years [2008 2009]}
                     {:full-name "Anders" :years [2007 2008]}])
      :body (hiccup-find :h4.mtn))
 => (list [:h4.mtn "Anders" " " [:span.tiny.shy "2007-2008"]]
          [:h4.mtn "Christian" " " [:span.tiny.shy "2008-2009"]]
          [:h4.mtn "Magnar" " " [:span.tiny.shy "2011"]]))

(fact (->> (page :endorsements [{:project :finn-reise
                                 :person {:first-name "Magnar"
                                          :full-name "Magnar Sveen"
                                          :url "/magnar/"
                                          :thumb "/photos/people/magnar/side-profile.jpg"}
                                 :author "Geir Pettersen"
                                 :photo "/thumbs/faces/geir-pettersen.jpg"
                                 :quote "Jeg liker brettspill, og det gjør Magnar og."}])
           :body html)

      => (html [:h2 "Referanser"]
               [:div.media
                [:img.img.thumb.mts {:src "/thumbs/faces/geir-pettersen.jpg"}]
                [:div.bd
                 [:h4.mtn "Geir Pettersen"
                  [:span.tiny " om "
                   [:a {:href "/magnar/"} "Magnar"]]]
                 [:p [:q "Jeg liker brettspill, og det gjør Magnar og."]]]]))

(fact (->> (page :tech [{:name "Java"}
                        {:name "Clojure", :url "/clojure/"}])
           :body html)

      => (html [:h3 "Teknologi"]
               [:p "Java og " [:a {:href "/clojure/"} "Clojure"] "."]))

(fact (->> (page :site "http://finn.no", :illustration nil)
           :aside html)
      => (html [:p [:a {:href "http://finn.no"} "finn.no"]]))

(fact (->> (page :site "https://finn.no", :illustration nil)
           :aside html)
      => (html [:p [:a {:href "https://finn.no"} "finn.no"]]))
