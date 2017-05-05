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
  (let [norm-s (normalize-plaintext s)]
    (mapv (partial apply str)
          (partition-all (square-size norm-s) norm-s))))

(defn ciphertext
  "Encodes given plaintext using crypto sqaure cipher."
  [s]
  (let [segments (plaintext-segments s)
        padding-len (- (count (first segments))
                       (count (last segments)))]
    (->> segments
         ;; since the last segment can have different length then the first ones
         ;; we need to pad it with the lengt of other segments to make sure
         ;; that all characters will get to the final result
         (map #(concat % (repeat padding-len nil)))
         (apply (partial map str))
         str/join)))

(ciphertext "Time is an illusion. Lunchtime doubly so.")

;; TODO: work in progress - need to find a proper way how to split final output
(defn normalize-ciphertext
  "Breaks encrypted text into the blocks of `square-size` length.
  If the encrypted text doesn't fit perfectly into the square, the last n empty spaces
  are distributed among last n rows."
  [s]
  (let [encrypted   (ciphertext s)
        row-count   (square-size encrypted)
        split-index (* row-count
                       (- row-count
                          (- (* row-count row-count)
                             (count encrypted))))
        first-part  (subs encrypted 0 split-index)
        second-part (subs encrypted split-index)]
    (->> (concat
          (map (partial apply str) (partition-all row-count first-part))
          (map (partial apply str) (partition-all (dec row-count) second-part)))
        (str/join " "))
    ))

(normalize-ciphertext "Time is an illusion. Lunchtime doubly so.")
