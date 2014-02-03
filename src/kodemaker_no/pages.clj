(ns kodemaker-no.pages
  (:require [kodemaker-no.pages.people-page :refer [all-people]]
            [kodemaker-no.pages.person-pages :refer [person-pages]]
            [kodemaker-no.pages.tech-pages :refer [tech-pages]]
            [kodemaker-no.pages.article-pages :refer [article-pages]]
            [kodemaker-no.pages.project-pages :refer [project-pages]]
            [kodemaker-no.pages.references-page :refer [references-page]]
            [kodemaker-no.pages.blog-post-pages :refer [blog-post-pages]]
            [stasis.core :as stasis]))

(defn general-pages [content]
  {"/mennesker/" (partial all-people (vals (:people content)))
   "/referanser/" (partial references-page (vals (:projects content)))})

(defn create-pages [content]
  (stasis/merge-page-sources
   {:person-pages (person-pages (vals (:people content)))
    :tech-pages (tech-pages (vals (:tech content)))
    :project-pages (project-pages (vals (:projects content)))
    :article-pages (article-pages (:articles content))
    :blog-post-pages (blog-post-pages (:blog-posts content))
    :general-pages (general-pages content)}))
