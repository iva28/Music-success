(ns menu
  (:require [csv_load]
            [shuffle_functions]
            [distance_functions]
            [csv_load]
            [shuffle_dataset]
            [conversion]
            [utility_functions :as ut]
            [predictions :refer [predict-songs-success-by-name
                                 top-3-closest-songs-by-name]]))

(def stones-csv (csv_load/read-csv "src/dataset/shuffled_songs.csv"))
(def stones-songs (conversion/convert-to-float-list-of-lists (rest stones-csv)))
(declare main-menu)
(declare song-menu)
(declare song-menu-details)
(declare specific-song-details)

(defn option1
  []
  ;(println "Successfully chosen option 1.")
  (ut/print-sequence (ut/format-song-row-number
                     (csv_load/modified-songs-and-index "src/dataset/shuffled_songs.csv")))
  (song-menu))


(defn specific-song-details
  [song]
    (println "Current song:")
    (utility_functions/format-print-song song)
    (println)
    (println "1.Find if the song is a hit")
    (println "2.Find 3 most similar songs")
    (println "3.Find other songs on the same album")
    (println "4.Go back to song main menu")
    (println "5.Go back to main menu")
    (println "6. For exiting the program enter -1")
    (println)
  (let [chosen-option (read-line)]
  (cond
    (= chosen-option "1") (do
                            (println)
                            (println (predict-songs-success-by-name stones-songs (first song)))
                            (println)
                            (specific-song-details song))
    (= chosen-option "2") (do
                            (println)
                            (println "Top 3 closest songs to " (first song) ":")
                            (println (top-3-closest-songs-by-name stones-songs (first song)))
                            (println)
                            (specific-song-details song))
    (= chosen-option "3") (do
                            (println)
                            (println "Songs on the same album " (second song) ":")
                            (ut/print-just-song (utility_functions/find-same-songs-on-album stones-songs (second song)))
                            (println)
                            (specific-song-details song))
    (= chosen-option "4") (song-menu)
    (= chosen-option "5") (main-menu)
    (= chosen-option "-1") (do (println "Thank you.") (System/exit 0))
    :else (do
            (println "Uknown option, going back to main menu")
            (main-menu)))))


(defn song-menu-details []
  (println)
  (println "1. Enter row number of a song you want to find more about")
  (println "2. Go back to song main menu")
  (println "3. Go back to main menu")
  (println "4. For exiting the program enter -1")
  (println)
  (let [chosen-option (read-line)]
    (cond
      (and (number? (try (Integer/parseInt chosen-option) (catch Exception _ nil)))
           (<= 1 (Integer/parseInt chosen-option) (count stones-songs)))
     (specific-song-details (utility_functions/search-by-row-number stones-songs chosen-option))
      (= chosen-option "2") (main-menu)
      (= chosen-option "-1") (do (println "Thank you.") (main-menu))
      :else (do (println "Unknown option") (song-menu)))))

(defn song-menu []
  (println)
  (println "1.Do you want to find out more about any song?")
  (println "2. Go back to main menu")
  (println "3. For exiting the program enter -1")
  (println)
  (let [chosen-option (read-line)]
    (cond
      (= chosen-option "1") (song-menu-details)
      (= chosen-option "2") (main-menu)
      (= chosen-option "-1") (do (println "Thank you.") (main-menu))
      :else (do (println "Unknown option") (song-menu)))))



(defn main-menu []
  (loop []
    (println)
    (println "Choose an option:")
    (println "1. Display all songs:")
    (println "2. For exiting the program enter -1")
    (println)
    (let [chosen-option (read-line)]
      (cond
        (= chosen-option "1") (option1)
        (= chosen-option "-1") (do (println "Thank you.") (System/exit 0))
        :else (do (println "Unknown option") (recur))))))


;(main-menu)