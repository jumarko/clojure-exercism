(ns sublist)

(defn- sublist?
  "Returns true if vec1 is a subvector of vec2."
  [vec1 vec2]
  (some #(= vec1 %)
        (partition (count vec1) 1 vec2)))

(sublist? [1 2 3] [4 1 2 5 1 2 3 8])

(sublist? [3 4 5] [0 1 2 3 4 5])

(defn classify
  "Checks if the first list is a sublist of the second one or vice versa.
  Also checks for equality.
  Returns following results:
  `:equal` - both lists are equal
  `:sublist` - first list is a sublist of the second list
  `:superlist` - second list is a sublist of the first list
  `:unequal` - neither condition is true"
  [xs1 xs2]
  (if (= xs1 xs2)
    :equal
    (if (< (count xs1) (count xs2))
      (if (sublist? xs1 xs2)
        :sublist
        :unequal)
      (if (sublist? xs2 xs1)
        :superlist
        :unequal))))

;; (let [xs1 [1 2 3]
;;       xs2 [1 2 3]]
;;   (if (< (count xs1)
;;          (count xs2))
;;     )
;;   (cond
;;     (< (count xs1)
;;        (count xs2))

;;     (= (count xs1)
;;        (count xs2))
;;     :equal


;;     )
;;   (if (= (count xs1)
;;          (count xs2))))
