(ns gigasecond
  (:import (java.time.temporal ChronoField)
           (java.time ZonedDateTime
                      ZoneOffset)))

(defn from
  "Calculates a date which is 1000,000,000 seconds from given date.
  The input and output dates are represented as a vector [year month day]."
  [year month day]
  (let [start (ZonedDateTime/of year month day 0 0 0 0 ZoneOffset/UTC)
        end (.plusSeconds start 1000000000)]
    [(.getYear end) (.getMonthValue end) (.getDayOfMonth end)]))
