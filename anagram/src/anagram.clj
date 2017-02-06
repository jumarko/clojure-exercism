(ns anagram
  (:require [clojure.string :as s]))

(defn anagrams-for
  "Given the word and list of potential anagrams return a sublits of `candidates`
   which contains only true anagrams of given `word`"
  [word candidates]
  (let [word-frequencies (frequencies (s/lower-case word))
        candidates-frequencies (map (fn [word] [word (frequencies (s/lower-case  word))]) candidates)]
    (map
      (fn [[w f]] w)
      (filter
        (fn [[w f]] (and
                      (not= (s/lower-case w) (s/lower-case word))
                      (= f word-frequencies)))
        candidates-frequencies))))
