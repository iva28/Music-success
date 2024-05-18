(ns conversion
  (:require [distance_functions]
            [csv_load :refer [read-csv]]))

(def stones-csv (read-csv "src/dataset/shuffled_songs.csv"))
(def stones-songs (rest stones-csv))
(def first-song (first stones-songs))
(defn convert-to-float
  [x]
  (cond
    (number? x) (float x)
    (string? x) (try (Float/parseFloat x) (catch Exception e nil))
     :else x))

(defn convert-to-float-in-list
  [seq]
  (mapv #(if (or (= %1 2) (= %1 (dec (count seq))))
           (try
             (Integer/parseInt %2)
             (catch Exception _ %2))
           (try
             (Float/parseFloat %2)
             (catch Exception _ %2)))
        (range) seq))


(def updated (convert-to-float-in-list first-song))

;(doseq [el updated]
;  (println (type el)))

; Converting list of list
(defn convert-to-float-list-of-lists
  [list-list]
  (mapv #(convert-to-float-in-list %) list-list))

;(def converted-songs (convert-to-float-list-of-lists stones-songs))
