(ns sieve)

(defn sieve
  "Generates prime numbers up to given number `n` (inclusive)
  using Sieve of Eratosthenes"
  [n]
  {:pre [(> n 1)]}
  (loop [primes []
         candidates (range 2 (inc n))]
    (if-let [current-prime (first candidates)]
      (recur
       (conj primes current-prime)
       (filter #(not= 0 (mod % current-prime))
               (rest candidates)))
      primes)))
