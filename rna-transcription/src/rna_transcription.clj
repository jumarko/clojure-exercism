(ns rna-transcription)

(defn- to-rna-nucleotid
  "Transcripts given DNA nucleotid to its RNA complementary nucleotid."
  [dna-nucleotid]
  (case dna-nucleotid
    \G \C
    \C \G
    \T \A
    \A \U
    (throw (AssertionError. "Invalid nucleotid"))))

(defn to-rna [dna-strand]
  (apply str (map to-rna-nucleotid dna-strand)))
