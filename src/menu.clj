(ns menu
  (:require [csv_load]
            [shuffle_functions]
            [distance_functions]
            [csv_load]
            [shuffle_dataset]
            [conversion]
            [utility_functions :as ut]))

(def stones-csv (csv_load/read-csv "src/dataset/shuffled_songs.csv"))
(def stones-songs (conversion/convert-to-float-list-of-lists (rest stones-csv)))
(declare main-menu)
(declare song-menu)
(defn option1
  []
  (println "Successfully chosen option 1.")
  (ut/print-sequence (ut/format-song-row-number
                     (csv_load/modified-songs-and-index "src/dataset/shuffled_songs.csv")))
  (song-menu))

(defn song-menu []
  (println "1. Enter row number of a song you want to find more about")
  (let [chosen-option (read-line)]
    (cond
      (and (number? (try (Integer/parseInt chosen-option) (catch Exception _ nil)))
           (<= 1 (Integer/parseInt chosen-option) (count stones-songs)))
     (utility_functions/format-print-song (utility_functions/search-by-row-number stones-songs chosen-option))
      (= chosen-option "-1") (do (println "Thank you.") (main-menu))
      :else (do (println "Unknown option") (song-menu)))))



(defn main-menu []
  (loop []
    (println "Choose an option:")
    (println "1. Display all songs:")
    (println "2. For exit enter -1:")
    (let [chosen-option (read-line)]
      (cond
        (= chosen-option "1") (option1)
        (= chosen-option "-1") (do (println "Thank you.") (System/exit 0))
        :else (do (println "Unknown option") (recur))))))


;(main-menu)