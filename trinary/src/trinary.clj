(ns trinary)

(defn- powers-of-three [exp]
  (nth (iterate (partial * 3) 1) exp))
#_(powers-of-three 4)

(def ^:private valid-digits #{\0 \1 \2})

(defn- to-digit
  "Converts character to trinary digit."
  [digit-char]
  (Character/digit digit-char 3))
#_(to-digit \2)

(defn to-decimal
  "Convers trinary number represented as a string to its decimal equivalent."
  [trinary-str]
  (if (every? valid-digits trinary-str)
    (let [order (dec (count trinary-str))]
      (reduce-kv
       (fn [decimal exp digit]
         (+ decimal
            (* (to-digit digit)
               (powers-of-three exp))))
       0
       (into [] (reverse trinary-str))))
    0))
#_(to-decimal "112")
