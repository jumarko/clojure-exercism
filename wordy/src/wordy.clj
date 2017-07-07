(ns wordy)

(def operations
  {"plus" +
   "minus" -
   "multiplied" *
   "divided" /})

(def ^:private operations-regex (clojure.string/join "|" (keys operations)))

(defn evaluate
  "Evaluate math expression."
  [expr]
  (if-let [[_ operand1 operation operand2]
           (re-matches (re-pattern (str "^What is (\\-?[0-9]+)\\s(" operations-regex ")\\s(\\-?[0-9]+)\\?"))
                       expr)]
    (do
      (println operand1 operation operand2)
      ((operations operation)
       (Integer/parseInt operand1)
       (Integer/parseInt operand2)))
    (throw (IllegalArgumentException. (str  "Not a math expression: " expr)))))

(evaluate "What is 9 plus -8?")
