(ns change)

(defn issue
  "Returns proper number of each coin to be used to represent
  total amount"
  [total coins]
  (let [sorted-coins (sort coins)]
    (-> (reduce
         (fn [z x]
           (->> (map #(conj (z (- x %)) %) sorted-coins)
                (filter vector?)
                (reduce (fn ([] nil) ([z x] (min-key count z x))))
                (assoc z x)))
         {0 []}
         (range 1 (inc total)))
        (get total)
        (or (throw (IllegalArgumentException. "cannot change"))))))

(issue 25 #{1 5 10 25 100})
(issue 23 #{1 4 15 20 50})
