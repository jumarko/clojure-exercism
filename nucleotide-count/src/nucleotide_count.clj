(ns nucleotide-count)

(def valid-nucleotides #{\A \C \G \T})

(defn count [nucleotide dna-string]
  (when-not (valid-nucleotides nucleotide)
    (throw (Exception. (str  "Invalid nucleotide ") nucleotide)))
  (clojure.core/count (filter #(= % nucleotide) dna-string) ))

(defn nucleotide-counts [dna-string]
  (into {}
    (for [nucleotide valid-nucleotides]
      [nucleotide (count nucleotide dna-string)])))

