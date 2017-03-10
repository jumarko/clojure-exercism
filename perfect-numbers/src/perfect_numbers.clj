(ns perfect-numbers)

(defn classify
  "Determines wheter given number is a perfect, abundant, or defficient,
  based on aliquot sum and Nicomachus classification scheme.
  Check https://en.wikipedia.org/wiki/Aliquot_sum."
  [n]
  (when (neg? n)
    (throw (IllegalArgumentException. "Number must not be negative!")))
  (let [aliquot-sum (apply +
                           (for [x (range 1 n)
                                 :when (zero? (mod n x))]
                             x))]
    (cond
      (= n aliquot-sum) :perfect
      (< n aliquot-sum) :abundant
      (> n aliquot-sum) :deficient)))

