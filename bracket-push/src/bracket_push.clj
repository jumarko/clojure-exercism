(ns bracket-push)

(def parens
  {\) \(
   \} \{
   \] \[})

(def opening-parens (set (vals parens)))
(def closing-parens (set (keys parens)))

(defn- is-paren? [char]
  (or (opening-parens char)
      (closing-parens char)))

(defn valid?
  "Checks whether all parens ('{', '(', '[') are matched and properly nested
  in given string."
  [string-with-parens]
  (if (seq
       (reduce (fn [parens-stack char]
                 (cond
                   (and (is-paren? char) (opening-parens char))
                   (conj parens-stack char)

                   (and (is-paren? char)
                        (= (peek parens-stack) (parens char)))
                   (pop parens-stack)

                   (is-paren? char)
                   (reduced [false])

                   :else
                   parens-stack))
               []
               string-with-parens))
    ;; if there are any remaining parens in stack after reading the whole string
    ;; we know that string contains unbalanced parentheses
    false
    true))

