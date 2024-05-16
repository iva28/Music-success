(ns parameters
  (:require [distance_functions]
            [csv_load :refer [read-csv]]))

(def stones-csv (read-csv "src/dataset/shuffled_songs.csv"))
(def stones-songs (rest stones-csv))

(def first-song (first stones-songs))
(def second-song (second stones-songs))

(println-str first-song)
(print (count first-song))

(defn convert-to-float
  [x]
  (cond
    (number? x) (float x)
    (string? x) (try (Float/parseFloat x) (catch Exception e nil))
     :else x))

(print (convert-to-float 2))
(print (convert-to-float "2"))
(print (convert-to-float "I2"))

(defn convert-to-float-in-list
  [lst]
  (mapv #(if-not (or (= %1 2) (= %1 (dec (count lst))))
           (if (try (Float/parseFloat %2) (catch Exception e nil))
             (try (Float/parseFloat %2) (catch Exception e %2))
             %2)
           %2)
        (range) lst))

(print (convert-to-float-in-list first-song))


