(ns grade-school)

(defn add
  "Adds student to the grade."
  [db student grade]
  ;; inspiration to use fnil: http://stackoverflow.com/questions/35328702/if-key-exists-update-otherwise-assoc
  (update db grade (fnil conj []) student))

