(ns binary)

(defn- power-of-two
  "Given exponent computes power of two: 2^exp"
  [exp]
  (nth (iterate (partial * 2) 1) exp))

(defn- map-binary-string-to-powers-of-two
  ""
  [binary-str]
  (let [order (dec (count binary-str))]
    (map-indexed
     (fn [idx binary-digit]
       (if (= \0 binary-digit)
         0
         (power-of-two (- order idx))))
     binary-str)))

(defn to-decimal
  "Converts binary number represented as string into decimal representation
  Invalid binary numbers (those that don't contain only 0s or 1s) are converted to zero."
  [binary-str]
  (if (every? #(or (= \0 %) (= \1 %)) binary-str)
    (apply + (map-binary-string-to-powers-of-two binary-str))
    0))

(to-decimal "1001")
