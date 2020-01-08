(ns ui.sections
  (:require [ui.elements :as e]
            [ui.layout :as l]
            [ui.icons :as icons]))

(defn article-section [{:keys [articles class] :as params}]
  [:div.section.article-section
   {:className class
    :style (l/stylish {} params)}
   [:div.content
    (map e/article articles)]])

(defn container-section [{:keys [content class] :as params}]
  [:div.section.container-section
   {:className class
    :style (l/stylish {} params)}
   [:div.content
    content]])

(defn banner-section [{:keys [logo text] :as params}]
  [:div.section
   {:style (l/stylish {} params)}
   [:div.content.tac.banner-ws
    (when logo
      [:img.banner-logo {:src logo}])
    (e/h1 {} text)]])

(defn bruce-section [{:keys [title text link image-front image-back] :as params}]
  [:div.section.bruce
   {:style (l/stylish {} params)}
   [:div.content
    [:div.gutter.gutter-l
     [:div.bruce-header (l/header)]
     [:div.bruce-content
      (e/h0 {} title)
      [:p.text text]
      (e/arrow-link link)]
     [:div.bruce-image-front
      [:img {:src image-front}]]
     [:div.bruce-image-back
      [:img {:src image-back}]]]]])

(defn enumeration-section [{:keys [title categories] :as params}]
  [:div.section.enumeration-section {:style (l/stylish {} params)}
   [:div.content
    [:h3.h3 title]
    [:div.enum-cats
     (for [category categories]
       [:div.enum-cat
        [:div.enum-label (:label category)]
        [:div.enum-items
         (e/comma-separated
          (for [item (:items category)]
            (if (:href item)
              [:a.link {:href (:href item)} (:text item)]
              (:text item))))]])]]])

(defn grid-section-content [{:keys [items grid-type]}]
  [:div.content
   ((case grid-type
      :box-grid e/box-grid
      :card-grid e/card-grid) items)])

(defn grid-section [params]
  [:div.section.grid-section
   {:style (l/stylish {} params)}
   (grid-section-content params)])

(defn grid-header-section [params]
  [:div.section.grid-header-section
   {:style (l/stylish {} params)}
   [:div.content.header-section
    (l/header)]
   [:div.grid-section
    (grid-section-content params)]])

(defn hip-section [{:keys [title single left right] :as params}]
  [:div.section.hip-section {:style (l/stylish {} (assoc params :background :blanc))}
   [:div.content
    (when single
      [:div.hip-single-box
       [:div.hip-title [:h3.h3 title]]
       [:div.hip-label.mbs (:title single)]
       [:img.hip-single-img {:src (:image single)}]
       [:div (:content single)]])
    (when (and left right)
      [:div.hip-pair
       [:div
        [:div.hip-left-box
         [:div.hip-title [:h3.h3 title]]
         [:div.hip-label.mbs (:title left)]
         [:div (:content left)]
         [:img.hip-left-img {:src (:image left)}]]]
       [:div.hip-right-box
        [:div.hip-right-inner
         [:div.hip-right-img.w-style-img {:style {:background-image (str "url(" (:image right) ")")}}]
         [:div.hip-label.mbs (:title right)]
         [:div (:content right)]]]])]])

(defn profile-section [{:keys [full-name title mobile mail description image presence cv] :as params}]
  [:div.section {:style (l/stylish {} params)}
   [:div.content.header-section
    (l/header)]
   [:div.content.profile-section.mbxl
    [:div.profile-title
     [:h1.h1 full-name]
     [:h5.h5.mbm title]]
    [:div.profile-image [:img.img {:src image}]]
    [:div.profile-cv
     (e/arrow-link {:text (:text cv)
                    :href (:url cv)})]
    [:div.profile-contact
     [:div [:a {:href (str "tel:" mobile)} mobile]]
     [:div [:a {:href (str "mailto:" mail)} mail]]
     (e/icon-link-row {:links presence :class "mtm"})]
    [:div.profile-desc
     description]]])

(defn pønt-section [{:keys [portrait-1 portrait-2 top-triangle bottom-triangle top-circle bottom-circle] :as params}]
  [:div.section.pønt-section {:style (l/stylish {} params)}
   [:div.content
    [:div.gutter.gutter-xl
     [:div.pønt-item.portrait-1
      [:img.img.image-style-chocolate-triangle {:src portrait-1}]]

     [:div.pønt-item.top-triangle
      [:img.img.image-style-rouge-triangle {:src top-triangle}]]

     [:div.pønt-item.bottom-circle
      [:img.img.image-style-chocolate-circle-pønt {:src bottom-circle}]]

     [:div.pønt-item.portrait-2
      [:img.img.image-style-rouge-triangle {:src portrait-2}]]

     [:div.pønt-item.top-circle
      [:img.img.image-style-rouge-circle-pønt {:src top-circle}]]

     [:div.pønt-item.bottom-triangle
      [:img.img.image-style-chocolate-triangle {:src bottom-triangle}]]]]])

(defn seymour-section [{:keys [seymours] :as params}]
  [:div.section {:style (l/stylish {} params)}
   [:div.content.whitespaceorama
    [:div.trigrid
     (for [seymour seymours]
       [:div
        (e/seymour seymour)])]]])

(defn titled-section [{:keys [title contents] :as params}]
  [:div.section.titled-section {:style (l/stylish {} params)}
   [:div.content
    [:div.titled-title
     [:h3.h3 title]]
    [:div.titled-content
     (interpose [:div.mbl] contents)]]])

(defn definition [{:keys [title contents]}]
  [:div.definition
   [:div.definition-title
    [:h3.h6 title]]
   [:div.definition-content
    (seq contents)]])

(defn definition-section [{:keys [definitions] :as params}]
  [:div.section.definition-section {:style (l/stylish {} params)}
   [:div.content
    [:hr.definition-separator]
    (->> definitions
         (map definition)
         (interpose [:hr.definition-separator]))
    [:hr.definition-separator]]])

(defn vertigo-section [{:keys [title text link image] :as params}]
  [:div.section.vertigo
   {:style (l/stylish {} params)}
   [:div.content
    [:div.gutter.gutter-l.grid
     {:style (l/add-pønt {} [{:kind :less-than
                              :position "right -300px top -410px"}])}
     [:div.vertigo-media
      [:div.inner-media
       [:img.img.image-style-vertigo {:src image}]]]
     [:div.vertigo-content
      [:div.inner-content
       (e/h2 {} title)
       [:p.text text]
       (e/arrow-link link)]]]]])

(defn widescreen-section [{:keys [image alt] :as params}]
  [:div.section.widescreen
   {:style (l/stylish {} params)}
   [:div.content
    [:div.content-l
     [:img.img {:src image :alt alt}]]]])

(defn cv-highlight [{:keys [title text href]}]
  [:div.cv-highlight
   [:h3.h4.mbs title]
   [:p text]
   (when href
     [:p.mts (e/arrow-link {:href href :text "Les mer"})])])

(defn cv-intro-section [{:keys [image friendly-name full-name title contact-lines links
                                experience qualifications quote description highlights]
                         :as params}]
  [:div.section.cv-section
   [:div.cv-wrapper
    {:style (->> (merge {:background :chablis
                         :pønt [{:kind :greater-than
                                 :position "left 0 top -280px"}]}
                        params)
                 (l/stylish {}))}
    [:div.content.header-section
     (l/header)]
    [:div.content.cv-content
     [:div.cv-image
      [:img
       {:src image
        :alt (str "Profilbilde av " full-name)}]]
     [:div.cv-essentials
      [:h1.h1 full-name]
      [:p.cv-title title]
      [:div.cv-details
       [:p.h6.cv-contact
        (interpose [:br] contact-lines)]
       (e/icon-link-row {:links links})]]]]
   [:div.content.cv-intro
    [:div.cv-intro-text
     [:div.mbxl
      [:h2.h4 experience]
      [:ul.dotted (for [q qualifications] [:li q])]]
     (when quote
       [:div.mbxxl
        [:div.mbm (e/blockquote {:quote (:text quote)})]
        (when-let [source (:source quote)]
          [:p [:cite "- " (:source quote)]])])
     [:h2.h3 "Om " friendly-name]
     [:div.text description]]
    [:div.cv-highlights
     (map cv-highlight highlights)]]])
