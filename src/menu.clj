(ns menu
  (:require [csv_load :refer [read-csv modified-songs-and-index]]
            [utility_functions :as ut]))

(defn option1
  []
   (println "Successfully chosen option 1.")
   (ut/print-sequence (ut/format-song-row-number
                        (modified-songs-and-index "src/dataset/shuffled_songs.csv"))))


(defn main-menu []
  (println "Choose an option:")
  (println "1. Display all songs:")
  (let [chosen-option (read-line)]
    (case chosen-option
      "1" (option1)
      (do
        (println "Unknown option")
        (main-menu)))))

(main-menu)