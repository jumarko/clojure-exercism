(ns prime-factors)

(defn of [n]
  (let [max-factor (int (Math/sqrt n))]
    (loop [cur-n n
           potential-factor 2
           prime-factors []]
        (cond
          (= cur-n 1) prime-factors

          ;; shortcut to avoid looping through all factors up to n if n is a prime number
          (> potential-factor max-factor) (conj prime-factors cur-n)

          (zero? (rem cur-n potential-factor))
          (recur (quot cur-n potential-factor)
                 potential-factor
                 (conj prime-factors potential-factor))
          
          :else
          (recur cur-n
                 (inc potential-factor)
                 prime-factors)
          ))))

(of 1)
(of 5)
(of 6)
(of 25)
(of 901255)
