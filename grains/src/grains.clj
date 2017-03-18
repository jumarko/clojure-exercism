(ns grains)

(def ^:private lazy-squares
  (iterate #(bigint (* 2 %)) 1))

(defn square
  "Compute number of grains on given square of chessboard.
  1 grain on the first square, 2 grains on second square, 4 grains on the third one, etc."
  [n]
  ;; notice that we need to cast intermediate result to bigint
  ;; otherwise we'll get overflow exception
  (nth lazy-squares (dec n)))

(defn total
  "Computes total number of grains on the chessboard.
  Check `square` for more details."
  []
  (apply + (take 64 lazy-squares)))

