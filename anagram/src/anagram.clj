(ns anagram
  (:require [clojure.string :as s]))

(defn- frequencies-match? [word]
  (let [word-frequencies (frequencies (s/lower-case word))]
    (fn  [[w f]] (and
                   (not= (s/lower-case w) (s/lower-case word))
                   (= f word-frequencies)))))

(defn anagrams-for
  "Given the word and list of potential anagrams return a sublits of `candidates`
   which contains only true anagrams of given `word`"
  [word candidates]
  (let [candidates-frequencies (map (fn [word] [word (frequencies (s/lower-case  word))]) candidates)]
    (->> candidates-frequencies
      (filter (frequencies-match? word))
      (map (fn [[w f]] w)))))
