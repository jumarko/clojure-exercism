(ns wordy)

(def operations
  {"plus" +
   "minus" -
   "multiplied by" *
   "divided by" /})

(def ^:private operations-regex (clojure.string/join "|" (keys operations)))
(def ^:private operation-with-operand-regex (re-pattern (str "(" operations-regex ")\\s(\\-?[0-9]+)")))
(def ^:private first-num-regex #"What is (\-?[0-9]+)")

(defn- parse-math-expr
  "Parses simple math expression like '1 plus 1 multiplied by 2 minus 10 divided by 3
  and returns its representation as vector of vectors where each inner vector
  has exactly 3 elements: [operand1 operation operand2].
  Only two operands are allowed for each operation.
  Returns `nil` if `expr` is not a valid expression."
  [expr]
  (let [[_ first-num]   (re-find first-num-regex expr)
        next-operations (re-seq operation-with-operand-regex expr)]
    (cond
      (not first-num)
      (throw (IllegalArgumentException. "Missing first operand."))

      (empty? next-operations)
      (throw (IllegalArgumentException. "No or unknown operations specified"))

      :else
      (into [first-num]
            (map (fn [[operation-expr operation operand]] [operation operand])
                 next-operations)))))

(parse-math-expr "What is -3 plus 7 multiplied by -2 minus 10?")
;; unknown operation
#_(parse-math-expr "What is 52 cubed?")

(defn evaluate
  "Evaluate math expression."
  [expr]
  (if-let [[first-num & ops] (parse-math-expr expr)]
    (reduce (fn [result [operation operand]]
              ((get operations operation) result (Integer/parseInt operand)))
            (Integer/parseInt first-num)
            ops)
    (throw (IllegalArgumentException.
            (str  "Not a math expression: " expr
                  ". Operation must be one of: " operations)))))
(evaluate "What is -3 plus 7 multiplied by -2?")
