(ns isogram)

(defn isogram?
  "Returns true if the string is an isogram."
  [s]
  (->>
   (clojure.string/lower-case s)
   frequencies
   (every? (fn [[character frequency]]
             (or (not (Character/isLetter character))
                 (= 1 frequency))))))
