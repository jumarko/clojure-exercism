(ns kindergarten-garden
  (:require [clojure.string :refer [lower-case split-lines]]))

(def ^:private plants
  {\C :clover
   \G :grass
   \R :radishes
   \V :violets})

;; default list of children
(def default-children ["Alice" "Bob" "Charlie" "David" "Eve" "Fred"
                       "Ginny" "Harriet" "Ileana" "Joseph" "Kincaid" "Larry"])

(defn- children-keywords-sorted
  "Sort children alphabetically and keywordize them - using lower case."
  [children]
  (->> children
       sort
       (map #(keyword (lower-case %)))))

(defn garden
  "Given the series of plants as a string where each plant is represented
  as a single character (R-radish, C-clover, V-violet, G-grass),
  returns a map containing all the children mapped to their plants."
  ([garden-definition]
   (garden garden-definition default-children))
  ([garden-definition children]
   (let [garden-rows (split-lines  garden-definition)
         garden-plants-rows (for [row garden-rows]
                              (partition-all 2 (map plants row)))]
     (apply merge-with concat (for [plants-row garden-plants-rows]
                                (zipmap (children-keywords-sorted children) plants-row))))))

(let [string   "VCRRGVRG\nRVGCCGCV"
      students ["Samantha" "Patricia" "Xander" "Roger"]
      surprise-garden (kindergarten-garden/garden string students)]
  surprise-garden)
