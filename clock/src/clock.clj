(ns clock)

;;; TODO: what's the best clock representation?
;;; vector?
;;; map?
;;; defrecord?

(defn clock
  "Clock that process time without date"
  [hour minute]
  [(mod hour 24) (mod minute 60)])

(defn clock->string
  "convers clock to human-redable string representation "
  [[hour minute]]
  ;; check format documentation and examples: https://clojuredocs.org/clojure.core/format
  (format "%02d:%02d" hour minute))

(defn add-time
  "Adds or substracts (if negative) hours and minutes from given clock."
  [clock hour minute])
