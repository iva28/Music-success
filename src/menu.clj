(ns menu
  (:require [csv_load]
            [shuffle_functions]
            [distance_functions]
            [csv_load]
            [shuffle_dataset]
            [conversion]
            [utility_functions :as ut]))
(declare main-menu)
(defn option1
  []
  (println "Successfully chosen option 1.")
  (ut/print-sequence (ut/format-song-row-number
                     (csv_load/modified-songs-and-index "src/dataset/shuffled_songs.csv")))
  (main-menu))


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