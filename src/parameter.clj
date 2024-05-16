(ns parameter
  (:require [csv_load :refer [read-csv]]
            [conversion :refer [convert-to-float-in-list
                                convert-to-float-list-of-lists]]
            [distance_functions :refer [calculate-euclidean-distance-csv]]
            [utility_functions :refer [find-by-name]]))

(def stones-csv (read-csv "src/dataset/modified_stones.csv"))
(def stones-songs (rest stones-csv))

(def first-song (first stones-songs))
(def second-song (second stones-songs))

(def first-song (convert-to-float-in-list first-song))
(def second-song (convert-to-float-in-list second-song))
(doseq [el first-song]
  (println (type el)))

(calculate-euclidean-distance-csv first-song second-song)