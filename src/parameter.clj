(ns parameter
  (:require [csv_load :refer [read-csv]]
            [conversion :refer [convert-to-float-list-of-lists]]
            [distance_functions :refer [calculate-euclidean-distance]]
            [utility_functions :refer [find-by-name]]))

(def stones-csv (read-csv "src/dataset/modified_stones.csv"))
(def stones-songs (rest stones-csv))

(defn calculate-3-closest-songs
  [song songs]
  ())