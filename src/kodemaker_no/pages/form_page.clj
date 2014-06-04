(ns kodemaker-no.pages.form-page
  (:require [kodemaker-no.formatting :refer [to-html]]))

(defn form-page []
  {:title "Hva kan vi hjelpe deg med?"
   :body (list
          [:p
           "For å hjelpe oss plukke ut oppdrag vi har tro på, vil vi at du
            fyller ut dette skjemaet. Vi tar kontakt med
            deg så snart vi har mulighet. Stort sett vil du høre fra oss innen to
            arbeidsdager."]
          [:form.form.mod {:action "/send-mail"
                           :method "POST"}
           [:label "Skriv litt om prosjektet ditt"]
           [:textarea.input {:rows 8, :name "tekst"}]
           [:div.line
            [:div.unit.r-1of2
             [:label "Når ønsker du å starte?"]
             [:input.input {:type "text", :name "oppstart"}]]
            [:div.lastUnit
             [:label "Hvilket omfang ser du for deg?"]
             [:input.input {:type "text", :name "omfang"}]]]
           [:label "Hvordan får vi tak i deg?"]
           [:input.input {:type "text", :name "kontakt"}]
           [:div
            [:button.btn {:type "submit"} "Send skjema"]]])})
