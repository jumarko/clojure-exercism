(ns hexadecimal
  (:require [clojure.string :refer [lower-case]]))

(def ^:private valid-digits {\0 0 \1 1 \2 2 \3 3 \4 4 \5 5 \6 6 \7 7
                             \8 8 \9 9 \a 10 \b 11 \c 12 \d 13 \e 14 \f 15})

(defn hex-to-int
  "Converts hexadecimal string into actual number."
  [hex-str]
  (let [hex-norm (lower-case hex-str)]
    (if (every? #(contains? valid-digits %) hex-norm)
      (reduce #(+ (* 16 %1)
                  (valid-digits %2))
              0
              hex-norm)
      0)))

(hex-to-int "10af8c")
