(ns beer-song
  (:require [clojure.string :as s]))

(defn verse [number]
  (cond
    (> number 2) (str 
                   number " bottles of beer on the wall, " number " bottles of beer.\n"
                   "Take one down and pass it around, " (dec  number) " bottles of beer on the wall.\n")
    (= number 2) (str 
                   number " bottles of beer on the wall, " number " bottles of beer.\n"
                   "Take one down and pass it around, " (dec  number) " bottle of beer on the wall.\n")
    (= number 1) (str
                   "1 bottle of beer on the wall, 1 bottle of beer.\n"
                   "Take it down and pass it around, no more bottles of beer on the wall.\n")
    (= number 0) (str
                   "No more bottles of beer on the wall, no more bottles of beer.\n"
                   "Go to the store and buy some more, 99 bottles of beer on the wall.\n")))

(verse 2)

(defn sing
  ([high]
   (sing high 0))
  ([high low]
   (->> (range low (inc high))
     reverse
     (map #(verse %))
     (s/join "\n"))))


