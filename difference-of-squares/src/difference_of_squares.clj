(ns difference-of-squares)

(defn square-of-sums
  "Computes the square of sum of all natural numbers from 1 to given n."
  [n]
  (let [sum (apply + (range 1 (inc n)))]
    (* sum sum)))

(defn sum-of-squares
  "Computes the sum of squares of all natural numbers from 1 to given n."
  [n]
  (reduce
   #(+ %1 (* %2 %2))
   0
   (range 1 (inc n))))

(defn difference
  "Finds the difference between the square of the sum
  and the sum of the squares of the first n natural numbers."
  [n]
  (- (square-of-sums n) (sum-of-squares n)))

