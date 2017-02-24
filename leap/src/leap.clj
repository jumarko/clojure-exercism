(ns leap)

(defn leap-year?
  "Checks if given year is a leap year.
  Following rules apply:
  - must be divisible by 4
  - cannot be divisible by 100 unless also divisible by 400"
  [year]
  (and
   (= 0 (mod year 4))
   (or (not= 0 (mod year 100))
       (and (= 0 (mod year 100))
            (= 0 (mod year 400))))))
