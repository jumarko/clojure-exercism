(ns robot-simulator)

(def ^:private directions [:north :east :south :west])

;; mapping between directions and coordinates which meaning is:
;; the transformation function (values) that should be applied if robot is facing given direction (key)
(def ^:private directions->coordinates
  {:north {:x identity :y inc}
   :east  {:x inc :y identity}
   :south {:x identity :y dec}
   :west  {:x dec :y identity}})

(def ^:private robot-commands
  {\L turn-robot-left
   \R turn-robot-right
   \A advance})

(defn- normalize-directions-index [index]
  (mod index (count directions)))

(defn- turn
  "Turns robot to new direction derived from `current-direction`
  using given function to compute new index (typically increment/decrement)."
  [current-direction index-fn]
  (let [current-index (.indexOf directions current-direction)
        new-index (normalize-directions-index (index-fn current-index))]
    (nth directions new-index)))

(defn- advance-coordinates
  [coordinates current-direction]
  (merge-with (fn [update-fn coordinate] (update-fn coordinate))
              (get directions->coordinates current-direction)
              coordinates))

(defn- advance
  "Advances robot one step in his current direction."
  [robot]
  (update robot :coordinates advance-coordinates (:bearing robot)))

(advance (robot {:x 10 :y -3} :south))

(defn robot
  "Creates a new robot at given coordinates facing given direction.
  Accepted directions are :north, :east, :south< :west.
  Coordinates advance to the north and east."
  [{:keys [x y]} direction]
  {:bearing direction
   :coordinates {:x x
                 :y y}})

(robot {:x -2 :y 1} :east)

;; exists only to satisfy contract required by test
(defn turn-right
  "Returns the new direction derived from `current-direction` by turning to the right."
  [current-direction]
  (turn current-direction inc))

(defn turn-robot-right
  "Turns robot to the right from its current direction.
  Returns a new robot facing the new direction."
  [robot]
  (assoc robot :bearing (turn-right (:bearing robot))))

;; exists only to satisfy contract required by test
(defn turn-left
  "Returns the new direction derived from `current-direction`
  by turning to the left."
  [current-direction]
  (turn current-direction dec))

(defn turn-robot-left
  "Turns robot to the left from its current direction.
  Returns a new robot facing the new direction."
  [robot]
  (assoc robot :bearing (turn-left (:bearing robot))))

(defn simulate
  "Simulate robot's movement using given commands.
  Each command is represented as a single character in `commands-str`:
  L -> Turn left
  R -> Turn right
  A -> Advance"
  [commands-str robot]
  (when-not (re-matches #"[LAR]+" commands-str)
    (throw (IllegalArgumentException.
            (str "Invalid command found in: " commands-str))))
  (reduce (fn [robot command-fn]
            (command-fn robot))
          robot
          (map robot-commands commands-str)))

(simulate "LAAARALA" (robot-simulator/robot {:x 0 :y  0} :north))
