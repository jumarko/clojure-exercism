(ns phone-number)

(defn number
  "Cleans up phone number so that it can be directly used to sent SMS messages.
  Only numbers with 10 or eleven digits are accepted.
  11-digit numbers have to start with 1 (which is then removed, only last 10 digits are used.)"
  [phone-num]
  (let [digits-only (apply str (filter #(Character/isDigit %) phone-num))
        digits-count (count digits-only)]
    (condp = digits-count
      (= digits-count 10) digits-only

      (and (= digits-count 11)
           (= \1 (first digits-only)))
      (.substring digits-only 1)

      "0000000000")))

(defn area-code
  "Extracts area code from phone number"
  [phone-num]
  (when-let [num (number phone-num)]
    (.substring num 0 3)))

(defn pretty-print
  "Formats phone number"
  [phone-num]
  )
