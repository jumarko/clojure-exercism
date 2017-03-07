(ns binary-search)

(defn- middle-index [vector]
  (quot (count vector) 2))

(defn middle [numbers-vector]
  (let [middle-idx (middle-index numbers-vector)]
    (if (pos? (count numbers-vector))
      (nth numbers-vector middle-idx)
      nil)))

(defn search-for [number numbers-vector]
  (let [index (.indexOf numbers-vector number)]
    (if (neg? index)
      (throw (Exception. (str "Number " number " not found.")))
      index)))

;; Following is my attempt to implement the binary search manually
;; Not completely working - not sure why
#_(defn search-for [number numbers-vector]
    (loop [numbers numbers-vector
           index 0]

      (let [middle-num (middle numbers)
            middle-idx (middle-index numbers-vector)]
        (cond
          (or (empty? numbers)
              (and (= 1 (count numbers)) (not= number middle-num)))
          (throw (Exception. (str "Number " number " not found.")))

          (= number middle-num) index

          (< number middle-num) (recur (subvec numbers-vector 0 (middle-index numbers-vector))
                                       0)

          (> number middle-num) (recur (subvec numbers-vector (inc  middle-idx) (count numbers-vector))
                                       (inc  middle-idx))))))
