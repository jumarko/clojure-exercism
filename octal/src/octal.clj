(ns octal)

(defn- valid-number?
  [octal-str]
  (re-matches #"[0-7]+" octal-str))

(defn- char->int [char]
  (Character/getNumericValue char))

(defn- octal->decimal [octal-str]
  (apply +
         (map-indexed
          ;; since 2^3 = 8, we just need to move 3x more to the left
          ;; than with ordinary binary numbers
          (fn [idx digit] (bit-shift-left (char->int digit) (* 3 idx)))
          (reverse octal-str))))

(defn to-decimal
  "Converts octal number represented as a string to its decimal equivalent.
  Invalid numbers are converted to zero."
  [octal-str]
  (if (valid-number? octal-str)
    (octal->decimal octal-str)
    0))

(to-decimal "011")
(to-decimal "abc")
(to-decimal "123")
