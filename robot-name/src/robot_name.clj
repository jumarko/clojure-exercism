(ns robot-name)

(defn- random-char []
  (rand-nth "ABCDEFGHIJKLMNOPQRSTUVWXYZ"))

(defn- random-digit []
  (rand-nth (range 10)))

(defn- random-name
  []
  (apply str
         [(random-char) (random-char) (random-digit) (random-digit) (random-digit)]))

(defn reset-name
  [robot]
  (swap! robot assoc ::name (random-name)))

(defn robot-name
  [robot]
  (::name @robot))

(defn robot
  "Creates new robot with factory settings"
  []
  (atom {::name (random-name)}))

(def robot1 (robot))
(robot-name robot1)
(reset-name robot1)
(robot-name robot1)

