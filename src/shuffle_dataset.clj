(ns shuffle_dataset
  (:require [csv_load :refer [read-csv save-modified-songs-csv]]
            [shuffle_functions :refer[add-random-element-from-list-of-list]]))

(def stones-csv (read-csv "src/dataset/modified_stones.csv"))
(count stones-csv)

;Shuffling origininal csv
(def column-names (first stones-csv))
;(print column-names)
(def stones-songs (rest stones-csv))

(def  shuffled-stones (add-random-element-from-list-of-list stones-songs))

(first stones-songs)
(first shuffled-stones)

;Save shuffled
(save-modified-songs-csv shuffled-stones column-names "src/dataset/shuffled_songs.csv")



