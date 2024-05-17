(ns predictions
  (:require  [parameter_experiments :refer [calculate-distance-for-chosen-song]]))

(def stones-csv (csv_load/read-csv "src/dataset/shuffled_songs.csv"))
(def stones-songs (conversion/convert-to-float-list-of-lists (rest stones-csv)))

;Provided with a name of a song
(defn predict-songs-success-by-name
  [seq song-name]
  (let [song (utility_functions/search-by-name-return-song seq song-name)
        all-except-one (utility_functions/all-songs-but-one seq song-name)
        distances-to-song (calculate-distance-for-chosen-song all-except-one song)
        sorted-distances (utility_functions/sort-by-distance distances-to-song)
        top-3-closest (take 3 sorted-distances)
        predicted-value (utility_functions/is-success top-3-closest)
        ]
    (if (= predicted-value 1)
      "Song will be hit!"
      "Song won't be hit :(")
    ))

(defn top-3-closest-songs-by-name
  [seq song-name]
  (let [song (utility_functions/search-by-name-return-song seq song-name)
        all-except-one (utility_functions/all-songs-but-one seq song-name)
        distances-to-song (calculate-distance-for-chosen-song all-except-one song)
        sorted-distances (utility_functions/sort-by-distance distances-to-song)
        top-3-closest (take 3 sorted-distances)
        ]
    (doseq [el top-3-closest]
      (print (first el) ", "))
    ))

;Provided with a row number of a song
(defn predict-songs-success-by-row
  [seq row]
  (let [song (utility_functions/search-by-row-number seq row)
        all-except-one (utility_functions/all-songs-but-one seq (first song))
        distances-to-song (calculate-distance-for-chosen-song all-except-one song)
        sorted-distances (utility_functions/sort-by-distance distances-to-song)
        top-3-closest (take 3 sorted-distances)
        predicted-value (utility_functions/is-success top-3-closest)
        ]
    (if (= predicted-value 1)
      "Song will be hit!"
      "Song won't be hit :(")
    ))

(defn top-3-closest-songs-by-row
  [seq row]
  (let [song (utility_functions/search-by-row-number seq row)
        all-except-one (utility_functions/all-songs-but-one seq (first song))
        distances-to-song (calculate-distance-for-chosen-song all-except-one song)
        sorted-distances (utility_functions/sort-by-distance distances-to-song)
        top-3-closest (take 3 sorted-distances)
        ]
    (doseq [el top-3-closest]
      (print (first el) ", "))
    ))

(def first-song (first stones-songs))
(def first-song (first first-song))

;(print (predict-songs-success-by-name stones-songs "2120 South Michigan Avenue"))
;(print (predict-songs-success-by-row stones-songs 1))

;(top-3-closest-songs-by-name stones-songs first-song)
;(top-3-closest-songs-by-row stones-songs 1)

