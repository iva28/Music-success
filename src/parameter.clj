(ns parameter
  (:require [csv_load :refer [read-csv]]
            [conversion :refer [convert-to-float-in-list
                                convert-to-float-list-of-lists]]
            [distance_functions :refer [calculate-euclidean-distance-csv]]
            [utility_functions :refer [find-by-name]]))

(def stones-csv (read-csv "src/dataset/modified_stones.csv"))
(def stones-songs (rest stones-csv))
(def updated-stones-songs (convert-to-float-list-of-lists stones-songs))

(def first-song (first updated-stones-songs))

;(defn calculate-3-closest
;  [song seq]
;  ((if find-by-name seq song)
;    ()
;    (calculate-euclidean-distance-csv )
;   ))