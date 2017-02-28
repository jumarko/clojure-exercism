(ns etl)

(defn- points->words [[points words]]
  (zipmap
   (map clojure.string/lower-case words)
   (repeat (count words) points)))

(defn transform
  "Transforms legacy scrabble data from mapping [Point -> Letters]
  to new formart [Letter -> Points]"
  [legacy-mapping]
  (->> legacy-mapping
       (map points->words)
       (into {})))

(transform {1 ["APPLE" "ARTICHOKE"], 2 ["BOAT" "BALLERINA"]})

