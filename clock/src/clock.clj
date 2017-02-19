(ns clock)

(defn- calc-hours
  "Calculate the final hours from given hours and minutes which both can be negative."
  [hours minutes]
  (mod (+ hours (Math/floorDiv minutes 60)) 24))


(defn- calc-minutes
  [minutes]
  (mod minutes 60))

(defn clock
  "Clock that process time without date"
  [hours minutes]
  [(calc-hours hours minutes)
   (calc-minutes minutes)])

(defn clock->string
  "convers clock to human-redable string representation "
  [[hour minute]]
  ;; check format documentation and examples: https://clojuredocs.org/clojure.core/format
  (format "%02d:%02d" hour minute))

(defn add-time
  "Adds or substracts (if negative) hours and minutes from given clock."
  [[clock-hour clock-minute] minute]
  (clock clock-hour (+ clock-minute minute)))
