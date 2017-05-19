(ns queen-attack
  (:require [clojure.string :refer [join]]))

  (defn board
    "Similar to `board-string` but represents a chessboard as a sequence (rows) of sequences (columns).
  Each field is represented with keyword:
  :e if it's empty
  :w if there's white queen
  :b if there's a black queen"
    [{white-queen-coordinates :w
      black-queen-coordinates :b}]
    (partition 8
               (for [x (range 8)
                     y (range 8)]
                 (cond
                   (= [x y] white-queen-coordinates) :w
                   (= [x y] black-queen-coordinates) :b
                   :else :e))))

(board {})
(board {:w [2 4] :b [6 6]})

(def ^:private symbol-mapping
  {:e \_
   :w \W
   :b \B})

(defn board-string
  "Return a string representing a chessboard.
  Rows are seperated by new lines.
  Columns are separated by spaces.
  Empty field is represented by underscore.
  White queen is represented by W.
  Black queen is represented by B.
  The rows and columns are numbered from 0."
  [{white-queen-coordinates :w
    black-queen-coordinates :b
    :as coordinates}]
  (str
   (join \newline
         (for [row (board coordinates)]
           (let [row-chars (map symbol-mapping row)
                 separated-row-chars (join \space row-chars)]
             (apply str separated-row-chars))))
   ;; notice that test expects the new line at the end of final row
   \newline))

(board-string {})
(board-string {:w [2 4] :b [6 6]})

(defn- same-diagonal? [[white-x white-y] [black-x black-y]]
  (= (- white-x black-x)
     (- white-y black-y)))

(defn can-attack
  "Returns true if queens can attack eac other, false otherwise."
  [{[white-x white-y] :w
   
    :as coordinates}]
  (cond
    (= white-x black-x) true
    (= white-y black-y) true
    (same-diagonal? [white-x white-y] [black-x black-y]) true
    :else false
    ))

(can-attack {:w [3 5] :b [2 4]})

