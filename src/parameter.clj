(ns parameter
  (:require [csv_load :refer [read-csv]]
            [conversion :refer [convert-to-float-list-of-lists]]
            [distance_functions :refer [calculate-euclidean-distance-csv]]
            [utility_functions :refer [all-songs-but-one sort-by-distance create-song-awards-map is-success]]))

(defn calculate-distance-for-chosen-song
  [song seq]
  (map #(conj % (calculate-euclidean-distance-csv song %)) seq))

(def stones-csv (read-csv "src/dataset/modified_stones.csv"))
(def stones-songs (convert-to-float-list-of-lists (rest stones-csv)))

(def first-song (first updated-stones-songs))

(calculate-euclidean-distance-csv first-song (all-songs-but-one stones-songs (first first-song)))

(def exluding-first (all-songs-but-one stones-songs (first first-song)))
(print (count stones-songs))
(print (count exluding-first))


(def distances-to-first-song (calculate-distance-for-chosen-song first-song exluding-first))
(def sorted-distances-to-first-song (sort-by-distance distances-to-first-song))

(def testing-songs (take 10 stones-songs))
(def testing-songs (create-song-awards-map testing-songs))
(print testing-songs)
(keys testing-songs)

;K=3
;one song
(def first-song (first stones-songs))
(print first-song)
(def distances-to-first-song (calculate-distance-for-chosen-song first-song (all-songs-but-one stones-songs first-song)))
(def distances-to-first-song (sort-by-distance distances-to-first-song))
(def top-3-closest (take 3 distances-to-first-song))

(def real-value (last first-song))
(def predicted-value (is-success top-3-closest))

(print real-value)
(print predicted-value)



