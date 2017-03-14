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

(defn- teenth-day-of-week-in-month
  [day-of-month]
  ;; max boundary for day of month is 19.
  ;; That means it will be 3rd week if the first day of month for the first week is <= 5
  ;; otherwise just second week.
  (if (#{1 2 3 4 5} day-of-month)
    3
    2))

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
    :teenth (teenth-day-of-week-in-month (.get calendar Calendar/DAY_OF_MONTH))
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
          (.set Calendar/DAY_OF_WEEK (day-of-week day-of-week-kw))
          ;; temporarily set day of week in month to the first one to make sure
          ;; that we are able to identify proper day of week in month later in `day-of-week-in-month` function.
          (.set Calendar/DAY_OF_WEEK_IN_MONTH 1))]
    (.set calendar Calendar/DAY_OF_WEEK_IN_MONTH (day-of-week-in-month day-of-week-in-month-kw calendar))
    [year
     month
     (.get calendar Calendar/DAY_OF_MONTH)]))

(meetup/meetup 5 2013 :sunday :teenth)
