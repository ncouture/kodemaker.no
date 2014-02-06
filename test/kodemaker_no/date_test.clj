(ns kodemaker-no.date-test
  (:require [kodemaker-no.date :refer :all]
            [midje.sweet :refer :all]
            [clj-time.core :as time]))

(fact "Parses date strings"
      (parse-ymd "2013-01-01") => (time/local-date 2013 1 1))

(fact "Formats dates as strings"
      (format-dmy (time/local-date 2013 1 1)) => "01.01.2013")

(fact "Formats dates cleverly"
      (clever-date (time/local-date 2013 1 1) (time/local-date 2013 1 1)) => "I dag"
      (clever-date (time/local-date 2013 1 2) (time/local-date 2013 1 1)) => "I morgen"
      (clever-date (time/local-date 2013 1 3) (time/local-date 2013 1 1)) => "3. Jan"
      (clever-date (time/local-date 2013 1 10) (time/local-date 2013 1 1)) => "10. Jan")
