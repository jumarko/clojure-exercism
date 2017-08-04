(ns secret-handshake)

(def numbers->commands
  ;; sorted in decreasing order
  (sorted-map-by >
   1 "wink"
   2 "double blink"
   4 "close your eyes"
   8 "jump"
   16 reverse))

(defn commands
  "Returns a sequence of commands representing secret handshake
  for given decimal number with following rules:
  ```
1 = wink
10 = double blink
100 = close your eyes
1000 = jump
10000 = Reverse the order of the operations in the secret handshake.
```"
  [n]
  (if (< n 32)
    (let [commands
          (second
           (reduce-kv (fn [[remainder commands] number command]
                        (if (>= (- remainder number) 0)
                          [(- remainder number) (conj commands command)]
                          [remainder commands]))
                      [n []]
                      numbers->commands))]
      (if (= reverse (first commands))
        ;; we are actually building commands in reverse order from the beginning
        (rest commands)
        (reverse commands)))
    []))
