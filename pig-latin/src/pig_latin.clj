(ns pig-latin
  (:require [clojure.string :as string]))

(def vowels #{\a \e \i \o \u})
(def consonants (->> (range (int \a)
                            (inc (int \z)))
                     (map char)
                     (remove vowels)
                     set))

(defn- move-prefix-to-end
  "Cut prefix of given length from given string and append it to the
  end of the remaining string optionally adding more suffixes."
  [s prefix-length & suffixes]
  (string/join (concat (drop prefix-length s)
                       (take prefix-length s)
                       suffixes)))

(move-prefix-to-end "thrush" 3)
(move-prefix-to-end "thrush" 3 "ay")

(defn- translate-word
  "Translates word(s) from English to Pig latin."
  [s]
  (cond

    (string/starts-with? s "qu")
    (move-prefix-to-end s 2 "ay")

    (or (vowels (first s))
        (string/starts-with? s "yt")
        (string/starts-with? s "xr"))
    (str s "ay")

    (= \y (first s))
    (move-prefix-to-end s 1 "ay")

    (and (consonants (first s))
         (string/starts-with? (string/join (drop 1 s)) "qu"))
    (move-prefix-to-end s 3 "ay")

    (consonants (first s))
    (let [consonants-count (count (take-while consonants s))]
      (move-prefix-to-end s consonants-count "ay"))))

(defn translate [phrase]
  (string/join " " (map translate-word (string/split phrase #" "))))



(comment
  (translate "apple")
  (translate "pig")
  (translate "thrush"))
