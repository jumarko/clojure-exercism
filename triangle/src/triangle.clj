(ns triangle)

(defn type
  "Determines the type of a triangle: :equilateral, :isosceles, :scalene, or :illogical.
  The inputs are the lengths of triangle's sides."
  [a b c]
  ;; sort the sides by their lengths to make the triangle inequality check simpler
  (let [[a b c] (sort [a b c])]
    (cond
      ;; triangle inequality
      (<= (+ a b) c) :illogical
      (= a b c) :equilateral
      (or (= a b) (= b c)) :isosceles
      :else :scalene)))

