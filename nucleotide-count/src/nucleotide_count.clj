(ns nucleotide-count)

(def valid-nucleotides #{\A \C \G \T})

(defn count [nucleotide dna-string]
  (when-not (valid-nucleotides nucleotide)
    (throw (Exception. (str  "Invalid nucleotide ") nucleotide)))
  (clojure.core/count (filter #(= % nucleotide) dna-string) ))

(defn nucleotide-counts [dna-string]
  (->>
    (group-by valid-nucleotides dna-string)
    (map (fn [[k v]] [k (clojure.core/count v)]))
    (into {\A 0 \T 0 \C 0 \G 0})))

