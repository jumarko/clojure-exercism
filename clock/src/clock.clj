(ns clock)

(defn- minutes->hours
  "Converts minutes that 'roll over' to hours.
   E.g. 81 minutes should be converted to 1 hour.
   -124 minutes should be converted to -2 hours."
  [minutes]
  (let [div (quot minutes 60)]
    (if (neg? minutes)
      ;; e.g. -40 minutes means that we need to substract one hour
      (dec div)
      ;; e.g. 59 minutes that we still return 0 hour 
      div)))

(defn- calc-hours
  "Calculate the final hours from given hours and minutes which both can be negative."
  [hours minutes]
  (mod (+ hours (minutes->hours minutes)) 24))

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
