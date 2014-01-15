(ns kodemaker-no.asciidoc-test
  (:require [kodemaker-no.asciidoc :refer :all]
            [midje.sweet :refer :all]))

(fact "We parse asciidoc pages."
      (parse-page "
= Systemutvikling på høyt nivå

:url: /kompetanse.html
:illustration: /photos/kolbjorn/side-profile-cropped.jpg

== :lead

Vi er et fokusert selskap.

== Teknologisk i front

Svært god teknologikunnskap.

== Konsulenttjenester innen systemutvikling

Våre konsulenter.

Med lang erfaring.

== Språk, plattformer og utviklingsmiljøer

Vi har både små og store.

== :aside

link:/kolbjorn.html[Kolbjørn Jetne]

``Erfaringer fra tøffere tider''
")
      => {:title "Systemutvikling på høyt nivå"
          :url "/kompetanse.html"
          :illustration "/photos/kolbjorn/side-profile-cropped.jpg"
          :lead "<p>Vi er et fokusert selskap.</p>"
          :article "<h2>Teknologisk i front</h2><p>Svært god teknologikunnskap.</p><h2>Konsulenttjenester innen systemutvikling</h2><p>Våre konsulenter.</p><p>Med lang erfaring.</p><h2>Språk, plattformer og utviklingsmiljøer</h2><p>Vi har både små og store.</p>"
          :aside "<p><a href=\"/kolbjorn.html\">Kolbjørn Jetne</a></p><p>&#8220;Erfaringer fra tøffere tider&#8221;</p>"})

(fact "Minimal page"
      (parse-page "
= Referanser

:url: /referanser.html

== :lead

Ja, har vi noen sånne da?
")
      => {:title "Referanser"
          :url "/referanser.html"
          :lead "<p>Ja, har vi noen sånne da?</p>"})
