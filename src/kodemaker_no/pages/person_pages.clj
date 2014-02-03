(ns kodemaker-no.pages.person-pages
  (:require [kodemaker-no.formatting :refer [to-html comma-separated]]
            [kodemaker-no.markup :as markup]
            [hiccup.core :as hiccup]
            [clojure.string :as str]))

(defn render-tech-bubble [tech]
  (when-not (empty? tech)
    [:p.near.cookie-w
     [:span.cookie (interpose " " (map markup/link-if-url tech))]]))

(defn- render-recommendation [{:keys [title tech blurb link]}]
  (list [:h3 title]
        (render-tech-bubble tech)
        (markup/append-to-paragraph
         (to-html blurb)
         (list " " (markup/render-link link)))))

(defn- render-recommendations [recs person]
  (list [:h2 (str (:genitive person) " anbefalinger")]
        (map render-recommendation (take 3 recs))))

(defn- render-hobby [{:keys [title description url illustration]}]
  [:div.bd
   [:h3.mtn title]
   (markup/prepend-to-paragraph
    (to-html description)
    (when illustration
      (if url
        [:a.illu {:href url} [:img {:src illustration}]]
        [:img.illu {:src illustration}])))])

(defn- render-hobbies [hobbies _]
  (list [:h2 "Snakker gjerne om"]
        (map render-hobby hobbies)))

(defn- inline-list [label nodes]
  (list [:strong label]
        (comma-separated nodes)
        "<br>"))

(defn- render-tech [{:keys [favorites-at-the-moment want-to-learn-more]} _]
  [:p
   (when favorites-at-the-moment
     (inline-list "Favoritter for tiden: " (map markup/link-if-url favorites-at-the-moment)))
   (when want-to-learn-more
     (inline-list "Vil lære mer: " (map markup/link-if-url want-to-learn-more)))])

(defn- render-presentation [{:keys [urls title thumb blurb]}]
  [:div.media
   [:a.img.thumb.mts {:href (or (:video urls)
                                (:slides urls)
                                (throw (Exception. (str "Missing url to video or slides in presentation " title))))}
    [:img {:src thumb}]]
   [:div.bd
    [:h3.mtn title]
    [:p blurb
     (when-let [url (:video urls)] (list " " [:a.nowrap {:href url} "Se video"]))
     (when-let [url (:slides urls)] (list " " [:a.nowrap {:href url} "Se slides"]))
     (when-let [url (:source urls)] (list " " [:a.nowrap {:href url} "Se koden"]))]]])

(defn- render-presentations [presentations person]
  (list [:h2 (str (:genitive person) " foredrag")]
        (map render-presentation presentations)))

(defn- render-endorsement [{:keys [photo author title project quote]}]
  [:div.media
   (when photo [:img.img.thumb.mts {:src photo}])
   [:div.bd
    [:h3.mtn author]
    (when (or title project)
      [:p.near
       (when title title)
       (when (and title project) ", ")
       (when project (markup/link-if-url project))])
    [:p [:q quote]]]])

(defn- render-endorsements [endorsements person]
  (list [:h2 (str (:genitive person) " referanser")]
        (map render-endorsement endorsements)))

(defn- render-presence-item [item]
  [:div.presence
   [:a {:href (str (:baseUrl item) "/" (:nick item))}
    [:img {:src (str "/logos/" (:logo item)) :title (:title item)}]]])

(defn- render-presence [presence]
  [:div.mod
   (when-let [cv (-> presence :cv)]
     (render-presence-item {:baseUrl "http://www.kodemaker.no/cv" :nick cv :logo "cv.png" :title "Cv"}))
   (when-let [li (-> presence :linkedin)]
     (render-presence-item {:baseUrl "http://www.linkedin.com" :nick li :logo "linkedin.png" :title "LinkedIn"}))
   (when-let [tw (-> presence :twitter)]
     (render-presence-item {:baseUrl "http://www.twitter.com" :nick tw :logo "twitter.png" :title "Twitter"}))
   (when-let [so (-> presence :stackoverflow)]
     (render-presence-item {:baseUrl "http://www.stackoverflow.com" :nick so :logo "stackoverflow.png" :title "StackOverflow"}))
   (when-let [gh (-> presence :github)]
     (render-presence-item {:baseUrl "http://github.com" :nick gh :logo "github.png" :title "GitHub"}))
   (when-let [cw (-> presence :coderwall)]
     (render-presence-item {:baseUrl "http://www.coderwall.com" :nick cw :logo "coderwall.png" :title "Coderwall"}))])

(defn- render-aside [{:keys [full-name title phone-number email-address presence]}]
  [:div.tight
   [:h4 full-name]
   [:p
    title "<br>"
    [:span.nowrap phone-number] "<br>"
    [:a {:href (str "mailto:" email-address)} email-address]]
   (when (seq presence) (render-presence presence))])

(defn- render-blog-post [{:keys [title tech blurb url]}]
  (list
   [:h3 title]
   (render-tech-bubble tech)
   (markup/append-to-paragraph
    (to-html blurb)
    (list " " [:a {:href url} "Les posten"]))))

(defn- render-blog-posts [posts person]
  (list
   [:h2 (str (:genitive person) " bloggposter")]
   (map render-blog-post (take 3 posts))))

(defn- maybe-include [person kw f]
  (when (kw person)
    (f (kw person) person)))

(defn- person-page [person]
  {:title (:full-name person)
   :illustration (-> person :photos :half-figure)
   :lead (to-html (:description person))
   :aside (render-aside person)
   :body (list
          (maybe-include person :tech render-tech)
          (maybe-include person :recommendations render-recommendations)
          (maybe-include person :hobbies render-hobbies)
          (maybe-include person :blog-posts render-blog-posts)
          (maybe-include person :presentations render-presentations)
          (maybe-include person :endorsements render-endorsements))})

(defn person-pages [people]
  (into {} (map (juxt :url #(partial person-page %)) people)))
