(ns atbash-cipher)

(def ^:private cipher-block-size 5)

(defn- valid-char? [ch]
  (Character/isLetterOrDigit ch))

(defn- encode-char [ch]
  (let [lower-char (Character/toLowerCase ch)]
    (if (Character/isLetter ch)
      (char  (+ (int \a)
                (- (int \z) (int lower-char))))
      ch)))

(defn encode
  "Encodes given test using atbash cipher."
  [s]
  (->> s
       (filter valid-char?)
       (map encode-char)
       (partition-all cipher-block-size)
       (map (partial apply str))
       (clojure.string/join " ")))
