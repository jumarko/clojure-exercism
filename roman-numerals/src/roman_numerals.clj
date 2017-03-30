(ns roman-numerals)

(def roman-literals
  {1 ["I" "X" "C" "M"]
   2 ["II" "XX" "CC" "MM"]
   3 ["III" "XXX" "CCC" "MMM"]
   4 ["IV" "XL" "CD"]
   5 ["V" "L" "D"]
   6 ["VI" "LX" "DC"]
   7 ["VII" "LXX" "DCC"]
   8 ["VIII" "LXXX" "DCCC"]
   9 ["IX" "XC" "CM"]})

(defn numerals
  "Converts number to the string representing Roman Numerals.
  Number must be less than 4000."
  [n]
  (when (> n 3999)
    (throw (IllegalArgumentException. (str "Cannot process numbers larger than 3999. You provided: " n))))

  (loop [n n
         roman-numeral ""]
    (if (< n 1)
      roman-numeral
      (let [denumerator (cond
                          (> n 999) 1000
                          (> n 99) 100
                          (> n 9) 10
                          (> n 0) 1)
            [quotient reminder] ((juxt quot rem) n denumerator)
            roman-char (nth
                        (get roman-literals quotient)
                        (get {1 0, 10 1, 100 2, 1000 3} denumerator))]
        (recur reminder (str roman-numeral roman-char))))))

