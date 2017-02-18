(ns clock)

;;; TODO: what's the best clock representation?
;;; vector?
;;; map?
;;; defrecord?

(defn clock
  "Clock that process time without date"
  [hour minute]
  (let [minutes->hours (quot minute 60)
        hours (mod (+ hour minutes->hours) 24)
        minutes (mod minute 60)]
    [hours minutes]))

(defn clock->string
  "convers clock to human-redable string representation "
  [[hour minute]]
  ;; check format documentation and examples: https://clojuredocs.org/clojure.core/format
  (format "%02d:%02d" hour minute))

(defn add-time
  "Adds or substracts (if negative) hours and minutes from given clock."
  [clock hour minute])
