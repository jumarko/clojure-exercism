(ns flatten-array)

(defn flatten [list]
  (filter #(not (nil? %))
          (clojure.core/flatten list)))


