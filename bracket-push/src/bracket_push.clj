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
                 (if (is-paren? char)
                   (if (opening-parens char)
                     (conj parens-stack char)
                     (if-let [previous-paren (peek parens-stack)]
                       (if-not (= previous-paren (parens char))
                         (reduced [false])
                         (pop parens-stack))
                       (reduced [false])))
                   parens-stack))
               []
               string-with-parens))
    ;; if there are any remaining parens in stack after reading the whole string
    ;; we know that string contains unbalanced parentheses
    false
    true))

(valid? "([])")
