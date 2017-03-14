(ns meetup
  (:import (java.util Calendar
                      GregorianCalendar)))

(defn- day-of-week
  "Transforms keywordized day of week like :tuesday to a constant in `java.util.Calendar` like TUESDAY."
  [day-keyword]
  (case day-keyword
    :monday Calendar/MONDAY
    :tuesday Calendar/TUESDAY
    :wednesday Calendar/WEDNESDAY
    :thursday Calendar/THURSDAY
    :friday Calendar/FRIDAY
    :saturday Calendar/SATURDAY
    :sunday Calendar/SUNDAY))

(defn- day-of-week-in-month
  "transforms keywordized day of week in month like :first, :second, :third, :fourth, :last, or :teenth
  to the actual number representing day of week in month as defined by `java.util.Calendar/DAY_OF_WEEK_IN_MONTH`."
  [week-in-month calendar]
  (case week-in-month
    :first 1
    :second 2
    :third 3
    :fourth 4
    :last (.getActualMaximum calendar Calendar/DAY_OF_WEEK_IN_MONTH)
    ;; TODO :last and :teenth
))

(defn meetup
  "Calculate a day of meetup.
  Both `month` and `year` are plain numbers.
  `day-of-week-kw` and `day-of-week-in-month-kw` are keywords, e.g.:
  - :tuesday, :friday, :saturday, etc.
  - :first, :second, :third, :fourth, :last, :teenth
  The result is a vector `[year month day]`"
  [month year day-of-week-kw day-of-week-in-month-kw]
  (let [calendar
        (doto (GregorianCalendar.)
          ;; notice that java.util.Calendar numbers months from 0 to 11
          (.set Calendar/MONTH (dec month))
          (.set Calendar/YEAR year)
          (.set Calendar/DAY_OF_WEEK (day-of-week day-of-week-kw)))]
    (.set calendar Calendar/DAY_OF_WEEK_IN_MONTH (day-of-week-in-month day-of-week-in-month-kw calendar))
    [(.get calendar Calendar/YEAR)
     (inc (.get calendar Calendar/MONTH))
     (.get calendar Calendar/DAY_OF_MONTH)]))

(meetup 3 2013 :monday :first)
