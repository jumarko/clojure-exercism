(ns accumulate)

(defn accumulate
  "Transforms each element of given collection with supplied function."
  [f coll]
  (reduce #(conj %1 (f %2))
          []
          coll))

;; lazy version
;; inspired by Daniel Ligth: http://exercism.io/submissions/fe13785f2da7441989f9bf016660c29b
(defn lazy-accumulate
  "Transforms each element of given collection with supplied function."
  [f coll]
  (lazy-seq
   (cons (f (first coll))
         (accumulate f (rest coll)))))
