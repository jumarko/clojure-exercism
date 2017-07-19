(ns nth-prime)

(defn- prime? [n]
  (every? #(not= 0 (mod n %))
          (range 2
                 ;; inc is used to handle n = 4
                 (min n (inc (Math/ceil (Math/sqrt n)))))))

(defn- all-primes []
  (->> (range 2 (Integer/MAX_VALUE))
       (filter prime?)))

(defn nth-prime
  "Find the n-th prime"
  [n]
  (when-not (pos? n)
    (throw (IllegalArgumentException. (str "expected positive number, but got:" n))))
  (nth (all-primes) (dec n)))

#_(nth-prime 10000)
