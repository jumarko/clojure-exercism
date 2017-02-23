(ns robot-name)

(defn- random-char []
  (rand-nth "ABCDEFGHIJKLMNOPQRSTUVWXYZ"))

(defn- random-digit []
  (rand-nth (range 10)))

(defn robot-name
  []
  (apply str
         [(random-char) (random-char) (random-digit) (random-digit) (random-digit)]))

(defn robot
  "Creates new robot with factory settings"
  []
  {::name (random-name)})

