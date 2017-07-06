(ns pangram
  (:require [clojure.string :as string]))

(def letters #{\a \b \c \d \e \f \g \h \i \j \k \l \m
               \n \o \p \q \r \s \t \u \v \w \x \y \z})

(defn pangram?
  "Determines whether given string is a pangram, that is a sentence
  that contains every letter of alphabet at least once.
  There's no distinction between lower case and upper case letters."
  [s]
  (-> s
      string/lower-case
      set
      (every? letters)))

(pangram? "the quick brown fox jumps over the lazy dog")
