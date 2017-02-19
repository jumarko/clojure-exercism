(ns clock)

(defn- minutes->hours
  "Converts minutes that 'roll over' to hours.
   E.g. 81 minutes should be converted to 1 hour.
   -124 minutes should be converted to -2 hours."
  [minutes]
  (let [div (quot minutes 60)]
    (if (neg? minutes)
      (dec div)
      div)))

(defn clock
  "Clock that process time without date"
  [hour minute]
  (let [minutes-hours (minutes->hours minute)
        hours (mod (+ hour minutes-hours) 24)
        minutes (mod minute 60)]
    [hours minutes]))

(defn clock->string
  "convers clock to human-redable string representation "
  [[hour minute]]
  ;; check format documentation and examples: https://clojuredocs.org/clojure.core/format
  (format "%02d:%02d" hour minute))

(defn add-time
  "Adds or substracts (if negative) hours and minutes from given clock."
  [[clock-hour clock-minute] minute]
  (clock clock-hour (+ clock-minute minute)))
