(ns bank-account)

(defn- opened? [account]
  (= :open (:status @account)))

(defmacro ^:private with-opened [account & body]
  `(when (opened? ~account)
     ~@body))

(defn open-account
  "Opens given account. This is the first operation starting the account life."
  []
  (atom
   {:balance 0
    :status :open}))

(defn close-account
  "Closes the account. No further operations are allowed on given account,
  that is `nil` is returned by any other operation that will use closed account."
  [account]
  (do
    (swap! account assoc :status :close)
    account))

(defn get-balance
  "Returns current balance of the account."
  [account]
  (with-opened account
    (:balance @account)))

(defn update-balance
  "Updates balance of given account adding the amount."
  [account amount]
  (with-opened account
    (swap! account update :balance (partial + amount))))

