(ns acronym
  (:require [clojure.string :as string]))

(defn- capitalize-first-letter
  "Similar to clojure.string/capitalize but only makes first letter
  upper case without touching other letters."
  [s]
  (when (seq s)
    (str (.toUpperCase (subs s 0 1))
         (subs s 1))))

(defn- filter-uppercase-letters
  [words-seq]
  (mapcat (partial filter #(Character/isUpperCase %)) words-seq))

(defn acronym
  "Converts a phrase to its acronym.
  E.g. Portable Network Graphics -> PNG."
  [phrase]
  (as-> phrase $
        (string/replace $ #":.*" "")
        (string/split $ #"[ -]")
        (map capitalize-first-letter $)
        (filter-uppercase-letters $)
        (apply str $)))
