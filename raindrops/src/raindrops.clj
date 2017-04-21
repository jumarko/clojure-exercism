(ns raindrops)

(defn divisible?
 "Binary predicate which returns true if n is divisible by d."
 [n d]
 (zero? (mod n d)))

(defn convert
  "Converts given number into the string depending on the number's factors.
  The important factors are:
  - 3 => 'Pling'
  - 5 => 'Plang'
  - 7 => 'Plong'
  If number doesn't have any of these factors, then it's numerical representation
  is converted to a string representation without modification."
  [n]
  (cond-> nil
    (divisible? n 3) (str "Pling")
    (divisible? n 5) (str "Plang")
    (divisible? n 7) (str "Plong")
    :else (#(if % % (str n)))))

(convert 4)
(convert 10)
(convert 14)
(convert 21)
(convert 105)
