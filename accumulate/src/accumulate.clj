(ns accumulate)

(defn accumulate
  "Transforms each element of given collection with supplied function."
  [f coll]
  (reduce #(conj %1 (f %2))
          []
          coll))


