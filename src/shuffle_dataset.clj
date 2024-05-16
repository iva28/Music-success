(ns shuffle_dataset
  (:require [csv_load :refer [read-csv]]))

(def stones-csv (read-csv "src/dataset/modified_stones.csv"))
(count stones-csv)
