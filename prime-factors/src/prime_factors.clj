(ns prime-factors)

(defn of [n]
  (let [max-factor (int (Math/sqrt n))]
    (loop [cur-n n
           potential-factor 2
           prime-factors []]
      (if (> potential-factor max-factor)

        (if (> cur-n max-factor)
          (conj prime-factors cur-n)
          prime-factors)

        (if (zero? (rem cur-n potential-factor))
          (recur (quot cur-n potential-factor)
                 potential-factor
                 (conj prime-factors potential-factor))
          (recur cur-n
                 (inc potential-factor)
                 prime-factors))))))

(of 1)
(of 5)
(of 6)
(of 25)
(of 901255)
