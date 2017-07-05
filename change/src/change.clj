(ns change)


(defn- check-and-sort [used-coins expected-total]
  (if (= (apply + used-coins)
         expected-total)
    (sort used-coins)
    (throw (IllegalArgumentException. "cannot change"))))

(defn issue
  "Returns proper number of each coin to be used to represent
  total amount"
  [total coins]
  (let [sorted-coins (->> coins sort reverse)]
    (loop [remaining-total total
           used-coins []
           coins sorted-coins]
      (if-let [biggest-coin (first coins)]
        (if (>= remaining-total biggest-coin)
          (let [biggest-coins-count (quot remaining-total biggest-coin)
                subtract-amount (* biggest-coin biggest-coins-count)]
            (recur (- remaining-total subtract-amount)
                   (into used-coins (repeat biggest-coins-count biggest-coin))
                   (rest coins)))
          (recur remaining-total
                 used-coins
                 (rest cois)))
        (check-and-sort used-coins total)))))

(issue 25 #{1 5 10 25 100})
(issue 23 #{1 4 15 20 50})
