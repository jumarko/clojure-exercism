(ns luhn)

;;; Rules:
;;; - string must contain only digits and be longer than 1
;;; - spaces are removed before validation
;;; - double every second digit starting from the _right_
;;;   - if product is greater than 9, then substract 9
;;; - sum all of the digits and check if its divisible by 10

(defn- luhn-digit
  "For every second digit (i.e. those with odd index):
  multiplies given digit by two, substracting 9 if the result is greater than 9.
  Digits with even indexes are left intact - just converted to numbers."
  [idx digit]
  (let [digit-num        (Character/getNumericValue digit)
        result           (* 2 digit-num)
        normalized-digit (if (> result 9)
                           (- result 9)
                           result)]
    (if (zero? (rem idx 2))
      digit-num
      normalized-digit)))

(defn- luhn-sum
  "Accepts string representation of decimal number and computes Luhn sum.
  The number is expected to fit into java.lang.Long."
  [n-str]
  (apply +
         (map-indexed luhn-digit (reverse n-str))))

(defn valid? [number-str]
  (let [without-spaces (clojure.string/replace number-str #" +" "")]
    (and
     (> (count without-spaces) 1)
     (every? #(Character/isDigit %) without-spaces)
     (zero? (rem (luhn-sum without-spaces) 10)))))

(valid? "055 444 285")

