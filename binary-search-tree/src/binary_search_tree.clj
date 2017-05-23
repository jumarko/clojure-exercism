(ns binary-search-tree
  "Represents a binary search tree.
  Tree is represented as 3 element vector where first elem is a `value`,
  second element represent left subtree where all the elements are less than parent `value`,
  and third element represent right subtree where all the elements are greater than `value`.
  `value` should never be nil.
  Either of left or right subtree can be nil in case there are no children.")

(defn singleton
  "Returns a binary tree containing a single node."
  [value]
  [value nil nil])

(defn value
  "Returns value of the root of given tree"
  [tree]
  (first tree))

(defn left
  "Returns left subtree."
  [tree]
  (second tree))

(defn right
  "Returns right subtree."
  [tree]
  (last tree))

(defn insert
  "Inserts a single value into the tree to the appropriate location."
  [value tree]
  (if-let [root (first tree)]
    (if (<= value root)
      [root (insert value (left tree)) (right tree)]
      [root (left tree) (insert value (right tree))])
    [value nil nil]))

(insert 2 (singleton 4))
(insert 4 (singleton 4))

(defn from-list
  "Populates a tree from given sequence."
  [xs]
  (reduce #(insert %2 %1)
          (singleton (first xs))
          (rest xs)))

(from-list [1 2 3])

(defn to-list
  "Returns a tree as a list."
  [[value left-t right-t]]
  (let [result 
        (cond
          (and (nil? left-t) (nil? right-t))
          [value]

          (nil? left-t)
          (flatten [value (to-list right-t)])

          (nil? right-t)
          (flatten [value (to-list left-t)])

          :else
          (flatten [value (to-list left-t) (to-list right-t)]))]
    (sort result)))

(to-list (insert 5 (insert 3 (insert 2 (singleton 4)))))
