(ns hamming)

(defn distance
  "Calculates hamming distance between two DNA strands
  Distance is defined only when the strands have equal length. Otherwise, nil is returned."
  [dna-1 dna-2]
  (when (= (count dna-1) (count dna-2))
    (->>
     (map vector dna-1 dna-2)
     (filter (fn [[x1 x2]] (not= x1 x2)))
     count)))

