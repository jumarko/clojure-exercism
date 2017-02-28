(ns etl)

(defn transform
  "Transforms legacy scrabble data from mapping [Point -> Letters]
  to new formart [Letter -> Points]"
  [legacy-mapping]
  (->> legacy-mapping
       (group-by (fn [[k v]] v))
       (map (fn [[points words]] [points (vec (map first words))]))
       (into {})))

(transform {"apple" 1 "artichoke" 1 "boat" 2 "ballerina" 2})

