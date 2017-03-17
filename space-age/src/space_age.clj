(ns space-age)

(defn- convert [seconds planet-to-earth-ration]
  (/ (on-earth seconds) planet-to-earth-ration))

(defn on-earth [seconds]
  (/ seconds 31557600))

(defn on-mercury [seconds]
  (convert seconds 0.2408467))

(defn on-venus [seconds]
  (convert seconds 0.61519726))

(defn on-mars [seconds]
  (convert seconds 1.8808158))

(defn on-jupiter [seconds]
  (convert seconds 11.862615))

(defn on-saturn [seconds]
  (convert seconds 29.447498))

(defn on-uranus [seconds]
  (convert seconds 84.016846))

(defn on-neptune [seconds]
  (convert seconds 164.79132))
