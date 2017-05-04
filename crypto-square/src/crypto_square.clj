(ns crypto-square
  (:require [clojure.string :as str]))

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
  [s]
  (int (Math/ceil (Math/sqrt (count s)))))

(defn plaintext-segments
  "Break input text into segments of size given by `square-size`"
  [s]
  (let [norm-s (normalize-plaintext s)
        col-count (square-size norm-s)
        diff (- (* col-count col-count) (count norm-s))
        blocks (if (zero? diff)
                 (partition col-count norm-s)
                 (let [row-count (dec col-count)
                       split-index (* diff row-count)]
                   (concat
                    (partition col-count (subs norm-s 0 split-index))
                    (partition row-count (subs norm-s split-index)))))]
    (mapv (partial apply str)
          blocks)))

(plaintext-segments "Time is an illusion. Lunchtime doubly so.")

(defn ciphertext
  "Encodes given plaintext using crypto sqaure cipher."
  [s]
  (->> s
       plaintext-segments
       (apply (partial map str))
       str/join))

(apply (partial map str)
       ["timeis" "anillu" "sionlu" "nchtim" "edoubl" "yso"])

(subs "123456789abcde" 0 8)

(ciphertext "Time is an illusion. Lunchtime doubly so.")

(defn normalize-ciphertext
  "Breaks encoded text into the blocks using `square-size`"
  [s])

