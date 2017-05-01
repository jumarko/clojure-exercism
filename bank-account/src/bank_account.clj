(ns bank-account)

(defn open-account
  "Opens given account. This is the first operation starting the account life."
  []
  (atom 0))

(defn close-account
  "Closes the account. No further operations are allowed on given account,
  that is `nil` is returned by any other operation that will use closed account."
  [account]
  (reset! account nil))

(defn get-balance
  "Returns current balance of the account."
  [account]
  @account)

(defn update-balance
  "Updates balance of given account adding the amount."
  [account amount]
  (swap! account + amount))

