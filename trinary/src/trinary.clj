(ns trinary)

(def ^:private ternary-digits {\0 0 \1 1 \2 2})

(defn to-decimal
  "Convers trinary number represented as a string to its decimal equivalent."
  [trinary-str]
  (if (every? ternary-digits trinary-str)
    (reduce
     (fn [decimal digit]
       (+
        (* decimal 3)
        (ternary-digits digit)))
     0
     trinary-str)
    0))
#_(to-decimal "112")
