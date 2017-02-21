(ns grade-school)

(defn add
  "Adds student to the grade."
  [db student grade]
  ;; inspiration to use fnil: http://stackoverflow.com/questions/35328702/if-key-exists-update-otherwise-assoc
  (update db grade (fnil conj []) student))


(defn grade
  "Returns all students in given grade."
  [db grade]
  (get db grade []))

(defn sorted
  "Returns students for each grade in ascending order by grades while ordering students in grade alphabetically."
  [db]
  (->> db
       (map (fn [[k v]] [k (sort v)]))
       (into (sorted-map))))

