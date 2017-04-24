(ns allergies)

(def allergens
  (into []
        {:cats 128
         :pollen 64
         :chocolate 32
         :tomatoes 16
         :strawberries 8
         :shellfish 4
         :peanuts 2
         :eggs 1})) 
(defn- lowest-unsupported-score
  "Returns lowest unsupported score, that is the next power of 2 which is NOT among `allergens`"
  []
  (let [highest-allergene-score (second (first allergens))]
    (* 2 highest-allergene-score)))

(defn- normalize-score
  "Adjust score to count only with supported allergens.
  That means that while the score may include values from items not mentioned
  in `allergens` we just ignore them."
  [score]
  (rem score (lowest-unsupported-score)))

#_(normalize-score 1709) ;=> 173

(defn allergies
  "Returns list of items that the person with given allergic score is allergic to."
  [score]
  (->
   (reduce
    (fn [[person-allergens total-score] [allergen allergen-score]]
      (if (>= total-score allergen-score)
        [(conj person-allergens allergen) (- total-score allergen-score)]
        [person-allergens total-score]))
    [[] (normalize-score score)]
    allergens)
   first
   reverse))

(allergies 259)
(allergies 1)

(defn allergic-to?
  "Decides wheter a person with given allergic score is allergic to the given item."
  [score item]
  (let [score-allergies (allergies score)]
    (some #{item} score-allergies)))

(allergic-to? 259 :eggs)
(allergic-to? 259 :shellfish)
