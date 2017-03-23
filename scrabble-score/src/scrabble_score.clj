(ns scrabble-score)

(def ^:private letter-values
  [[#{\A \E \I \O \U \L \N \R \S \T} 1]
   [#{\D \G} 2]
   [#{\B \C \M \P} 3]
   [#{\F \H \V \W \Y} 4]
   [#{\K} 5]
   [#{\J \X} 6]
   [#{\Q \Z} 7]])

(defmulti  score-letter type)

(defmethod score-letter String [letter]
  (score-letter (first letter)))

(defmethod score-letter Character [letter]
  (some
   (fn [letters-point]
     (when ((first letters-point) (Character/toUpperCase letter))
       (second letters-point)))
   letter-values))

(defn score-word
  "Scores the scrabble word based on values defined for each letter."
  [word]
  (apply + (map score-letter word)))

(score-word "quirky")
