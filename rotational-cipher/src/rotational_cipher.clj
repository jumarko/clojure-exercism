(ns rotational-cipher
  (:require [clojure.string :as string]))

(defn- rotate-char [key ch]
  (if (Character/isLetter ch)
    (let [start-char-code (if (Character/isUpperCase ch)
                            (int \A)
                            (int \a))]
      (char (+ start-char-code
               (mod
                (+
                 (- (int ch) start-char-code)
                 key)
                26))))
    ;; other characters such as spaces and punctuation are unmodified.
    ch))

(rotate-char 13 \n)

(defn rotate
  "Encrypts given text using rotational cipher with given key.
  Key is has to be an integer which wraps to interval <0,25>.
  Negative values are possible with meaning to go in opposite direction.
  Values larger than 25 are possible and they wrap around, meaning 'a' encoded with key 26 is 'a' again."
  [text key]
  (->> text
       (map (partial rotate-char key))
       string/join))
