(ns bob
  (:require [clojure.string :refer [trim upper-case]]))

(defn question? [s]
  (= \? (last s)))


(defn response-for
  "Returns bob response for anyhing."
  [s]
  (let [trim-s (trim s)]
    (cond
      (empty? trim-s) "Fine. Be that way!"
      ;; only numbers and interpunction
      (not-any? #(Character/isLetter %) trim-s) (if (question? trim-s) "Sure." "Whatever.")
      (= trim-s (upper-case s)) "Whoa, chill out!"
      (= \? (last trim-s) ) "Sure."
      :else "Whatever.")))





