(ns shuffle_dataset
  (:require [csv_load :refer [read-csv save-modified-songs-csv]]
            [shuffle_functions :refer[add-random-element-from-list-of-list]]
            [utility_functions :refer [prepare-unknown-dataset]]))

(def stones-csv (read-csv "src/dataset/modified_stones.csv"))

;Shuffling original csv
(def column-names (first stones-csv))
(def stones-songs (rest stones-csv))
(def  shuffled-stones (add-random-element-from-list-of-list stones-songs))
;Save shuffled
(save-modified-songs-csv shuffled-stones column-names "src/dataset/shuffled_songs.csv")



;Splitting shuffled dataset into known and known songs
;Idea is to use knn algorithm to classify songs into hits and not hits
(def shuffled-ds (read-csv "src/dataset/shuffled_songs.csv"))
(def shuffled-ds-songs (rest shuffled-ds))
(def split-count (int (* (count shuffled-ds-songs) 0.1)))
;(print split-count)

(def unknown-songs (prepare-unknown-dataset (take split-count shuffled-ds-songs)))
;(print (count unknown-songs))

(def known-songs (drop split-count shuffled-ds-songs))
;(print (count known-songs))

;Songs we want to classify
(save-modified-songs-csv unknown-songs (first shuffled-ds) "src/songs/unknown_songs.csv")

;Songs that are known
(save-modified-songs-csv known-songs (first shuffled-ds) "src/songs/known_songs.csv")

