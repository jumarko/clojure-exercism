 (ns all-your-base)

(defn- valid-digit? [digit base]
  (and (> base digit)
       (>= digit 0)))

(defn- to-number [base digits-seq]
  (reduce (fn [acc digit]
            (if (valid-digit? digit base)
              (+ digit (* base acc))
              (reduced nil)))
          0
          digits-seq))

(defn- from-number [n output-base]
  (loop [n n
         b output-base
         output-digits '()]
    (let [div (quot n b)
          modus (mod n b)]
      (if (pos? div)
        (recur div b (conj output-digits modus))
        (conj output-digits modus)))))

(defn convert
  "Converts number represented by sequence of digits (second arg)
  in any base (first arg) to another number in different base (third arg)."
  [base digits-seq output-base]
  (when (and (> base 1)
             (> output-base 1))
    (if (seq digits-seq)
      (when-let [n (to-number base digits-seq)]
        (from-number n output-base))
      '())))
