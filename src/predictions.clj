(ns predictions
  (:require  [parameter_experiments :refer [calculate-distance-for-chosen-song]]))

(def stones-csv (csv_load/read-csv "src/dataset/modified_stones.csv"))
(def stones-songs (conversion/convert-to-float-list-of-lists (rest stones-csv)))

(defn predict-songs-success
  [seq song]
  (let [all-except-one (utility_functions/all-songs-but-one seq (first song))
        distances-to-song (calculate-distance-for-chosen-song all-except-one song)
        sorted-distances (utility_functions/sort-by-distance distances-to-song)
        top-3-closest (take 3 sorted-distances)
        predicted-value (utility_functions/is-success top-3-closest)
        ]
    (if (= predicted-value 1)
      "Song will be hit!"
      "Song won't be hit :(")
    ))

(defn top-3-closest-songs
  [seq song]
  (let [all-except-one (utility_functions/all-songs-but-one seq (first song))
        distances-to-song (calculate-distance-for-chosen-song all-except-one song)
        sorted-distances (utility_functions/sort-by-distance distances-to-song)
        top-3-closest (take 3 sorted-distances)
        ]
    (doseq [el top-3-closest]
      (print (first el) ", "))
    ))

(def first-song (first stones-songs))
(print (first first-song))

(print (predict-songs-success stones-songs first-song))
(top-3-closest-songs stones-songs first-song)