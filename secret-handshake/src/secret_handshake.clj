(ns secret-handshake)

(def numbers->commands
  {0 "wink"
   1 "double blink"
   2 "close your eyes"
   3 "jump"})

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
  (let [commands
        (->> (range 4)
             (filter #(bit-test n %))
             (map numbers->commands))]
    (if (bit-test n 4)
      (reverse commands)
      commands)))
