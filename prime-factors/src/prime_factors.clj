(ns prime-factors)

(defn of [n]
  (loop [cur-n n
         potential-factor 2
         prime-factors []]
    (if (< cur-n 2)
      (if (and (empty? prime-factors) (> n 1))
        [n]
        prime-factors)
      (if (zero? (rem cur-n potential-factor))
        (recur (quot cur-n potential-factor)
               potential-factor
               (conj prime-factors potential-factor))
        (recur cur-n
               (inc potential-factor)
               prime-factors)))))

(of 1)
(of 5)
(of 6)
(of 25)
(of 901255)
