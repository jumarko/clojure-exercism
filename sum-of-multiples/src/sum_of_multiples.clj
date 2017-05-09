(ns sum-of-multiples)

(defn- multiples
  "Returns a lazy sequence of multiples of given number starting from that number.
  E.g. for 3 it returns (3, 6, 9, 12, ...)."
  [n]
  (iterate (partial + n) n))

(defn- multiples-up-to
  "Returns a lazy sequence of all multiples of given number up to given upper bound (exclusive)."
  [upper-bound n]
  (take-while
   (partial > upper-bound)
   (multiples n)))

(defn sum-of-multiples
  "Given numbers, compute the sum of all their _unique_ multiples up to the `upper-bound` (exclusive).
  E.g. `(sum-of-multiples '(3 5) 10)` will return 23."
  [numbers upper-bound]
  (->> numbers
       (mapcat
        (partial multiples-up-to upper-bound))
       ;; make sure that duplicates are not counted multiple times
       set
       (apply +)))

(sum-of-multiples [3 5] 10)
(sum-of-multiples [3 5] 100)
