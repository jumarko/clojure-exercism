(ns minesweeper
  (:require [clojure.string :refer [join split]]))

(defn- make-board
  "Converts string representation of board to vector of strings (rows)."
  [board-str]
  (split board-str #"\n"))

(make-board (join \newline ["***"
                            "***"
                            "***"]))

(defn- star-neighbors-count
  "Returns the number of neighbors that are stars."
  [[x y] board]
  (count
   (for [dx [-1 0 1] dy [-1 0 1]
         :let [n-x (+ x dx)
               n-y (+ y dy)]
         :when (and (<= 0 n-x)
                    (<= 0 n-y)
                    (not (and (zero? dx) (zero? dy)))
                    (= \* (get-in board [n-x n-y])))]
     [n-x n-y])))

(defn draw
  "Draw minesweeper's board with empty cells replaced by numbers
  indicating how many adjacent mines are for each cell.
  Zeros are not drawn - that is the cells with 0 adjacent mines
  are left blank.
  Input board and output boards are represented as strings,
  with each character representing a column and rows separated by new lines."
  [mines-board]
  (let [input-board (make-board mines-board)
        output-board (for [x (range (count input-board))
                           y (range (count (get input-board x)))
                           :let [cell (get-in input-board [x y])
                                 neighbors-count (star-neighbors-count [x y] input-board)
                                 cell-output (if (and (= cell \space) (pos? neighbors-count))
                                               neighbors-count
                                               cell)]]
                       cell-output)]
    (->>
     (partition (count (first input-board)) output-board)
     (map join)
     (join \newline))))
