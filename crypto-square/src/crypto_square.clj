(ns crypto-square)

(defn normalize-plaintext
  "Normalizes input text by removing all whitespace / punctutation characters
  and making all chars lowercase."
  [s]
  (->> s
       (filter #(Character/isLetterOrDigit %))
       (apply str)
       clojure.string/lower-case))

(defn square-size
  "Returns number of columns, i.e. the number of chars in one block."
  [s])

(defn plaintext-segments
  "Break input text into segments of size given by `square-size`"
 [s])
(plaintext-segments "never do this")

(defn ciphertext
  "Encodes given plaintext using crypto sqaure cipher."
  [s])

(defn normalize-ciphertext
  "Breaks encoded text into the blocks using `square-size`"
  [s])

