(ns triangle)

(defn type
  "Determines the type of a triangle: :equilateral, :isosceles, :scalene, or :illogical.
  The inputs are the lengths of triangle's sides."
  [a b c]
  ;; sort the sides by their lengths to make the triangle inequality check simpler
  (let [sorted (sort [a b c])
        as (first sorted)
        bs (second sorted)
        cs (last sorted)]
    (cond
      ;; triangle inequality
      (<= (+ as bs) cs) :illogical
      (= as bs cs) :equilateral
      (or (= as bs) (= bs cs)) :isosceles
      :else :scalene)))

