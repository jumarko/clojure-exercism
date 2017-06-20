(ns largest-series-product)

(def valid-digits #{\0 \1 \2 \3 \4 \5 \6 \7 \8 \9})

(defn- digit->int [digit]
  (Character/getNumericValue digit))

(defn- digits-product
  "Calculates product of digits represented as a sequence of characters."
  [digits]
  (->> digits
       (map digit->int)
       (apply *)))

(defn largest-product
  "Computes the largest product of `span` consecutive digits in given sequence of digits."
  [span digits]
  (cond
    (not (every? valid-digits digits))
    (throw (IllegalArgumentException. (str "Invalid digit(s): " digits)))

    (> span (count digits))
    (throw (IllegalArgumentException. (format "span=%d is greater than digits count=%d" span (count digits))))

    (neg? span)
    (throw (IllegalArgumentException. (str "Negative span: " span)))

    (and (zero? span) (empty? digits))
    1

    :else
    (->> digits
         (partition span 1)
         (map digits-product)
         sort
         last)))

(largest-product 0 "")
