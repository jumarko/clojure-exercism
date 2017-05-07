(ns pascals-triangle)

(defn- sum-elems
  ""
  [coll]
  (reduce #(+ %1 %2) coll))

(defn- triangle-row
  "Generates the next pascal triangle row."
  ([initial-row]
   (let [row (vec initial-row)]
     (mapv
      ;; use arbitrary precision to avoid integer overflow
      +'
      (cons 0 row)
      (conj row 0)))))

(def triangle (iterate triangle-row [1]))

(defn row [n]
  (nth triangle (dec n)))
