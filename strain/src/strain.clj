(ns strain)

(defn retain
  "Accepts a predicate fn and a collection and returns new collection containing
  elements for which the predicate returns true."
  [pred coll]
  (reduce
   (fn [acc x] (if (pred x) (conj acc x) acc))
   []
   coll))

(defn discard
  "Accepts a predicate fn and a collection and returns new collection containing
  elements for which the predicate returns false."
  [pred coll]
  (retain (complement pred) coll))
