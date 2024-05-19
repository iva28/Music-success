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

(def stones-csv (csv_load/read-csv "src/songs/unknown_songs.csv"))
(def stones-songs (conversion/convert-to-float-list-of-lists (rest stones-csv)))

;whole ds for album purposes
(def album-csv (csv_load/read-csv "src/dataset/shuffled_songs.csv"))

;comparison dataset - known songs and their success
(def compare-csv (conversion/convert-to-float-list-of-lists
                   (rest (csv_load/read-csv "src/songs/known_songs.csv"))))


(declare main-menu)
(declare song-menu)
(declare song-menu-details)
(declare specific-song-details)

(defn display-all-songs
  []
  (ut/print-sequence (ut/format-song-row-number
                     (csv_load/modified-songs-and-index "src/songs/unknown_songs.csv")))
  (song-menu))


(defn specific-song-details
  [song]
    (println "======================================== SONG'S SPECIFICATIONS ========================================")
    (println "Current song:")
    (print "\u001B[32m")
    (utility_functions/format-print-song song)
    (print "\u001B[0m")
    (println)
    (println "1. Find if the song is a hit")
    (println "2. Find 3 most similar songs")
    (println "3. Find other songs on the same album")
    (println "4. Go back to song main menu")
    (println "5. Go back to main menu")
    (println "6. For exiting the program enter -1")
    (println)
  (let [chosen-option (read-line)]
  (cond
    (= chosen-option "1") (do
                            (println)
                            (println "************************************** SONG'S PREDICTION ****************************************")
                            (print "\u001B[34m")
                            (println (predict-songs-success-by-name compare-csv song))
                            (println)
                            (print "\u001B[0m")
                            (Thread/sleep 2000)
                            (specific-song-details song))
    (= chosen-option "2") (do
                            (println)
                            (println "************************************** SONG'S TOP 3 MATCHES *******************************************")
                            (println "Top 3 closest songs to " (first song) ":")
                            (print "\u001B[34m")
                            (top-3-closest-songs-by-name compare-csv song)
                            (print "\u001B[0m")
                            (println)
                            (Thread/sleep 1000)
                            (specific-song-details song))
    (= chosen-option "3") (do
                            (println)
                            (println "************************************** SONG'S ON THE SAME ALBUM *******************************************")
                            (println "Songs on the same album " (second song) ":\n")
                            (print "\u001B[34m")
                            (ut/print-just-song (utility_functions/find-same-songs-on-album (rest album-csv) (second song)))
                            (print "\u001B[0m")
                            (println)
                            (Thread/sleep 1000)
                            (specific-song-details song))
    (= chosen-option "4") (song-menu)
    (= chosen-option "5") (main-menu)
    (= chosen-option "-1") (do (print "\u001B[35m") (println "Thank you.") (print "\u001B[0m") (System/exit 0))
    :else (do (print "\u001B[31m")
              (println "Unknown option, try again")
              (Thread/sleep 1000)
              (print "\u001B[0m")
              (specific-song-details song)))))


(defn song-menu-details []
  (println)
  (println "======================================== SONG MENU ========================================")
  (println "1. Enter row number of a song you want to find more about (1-29)")
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
      (= chosen-option "-1") (do (print "\u001B[35m") (println "Thank you.") (print "\u001B[0m") (System/exit 0))
      :else (do (print "\u001B[31m")
              (println "If you want to find out about a song type a number between 1 and 29")
                (println "If you want to go back to main menu enter 2")
                (println "If you want to exit the program enter -1")
                (println "Main menu will appear in 2 second for you to try again")
                (print "\u001B[0m")
                (Thread/sleep 2000)
                (song-menu)))))

(defn song-menu []
  (println)
  (println "======================================== MAIN MENU ========================================")
  (println "1. Do you want to find out more about any song?")
  (println "2. Go back to main menu")
  (println "3. For exiting the program enter -1")
  (println)
  (let [chosen-option (read-line)]
    (cond
      (= chosen-option "1") (song-menu-details)
      (= chosen-option "2") (main-menu)
      (= chosen-option "-1") (do (print "\u001B[35m") (println "Thank you.") (print "\u001B[0m") (System/exit 0))
      :else (do (print "\u001B[31m")
                (println "If you want to find out about any song enter 1")
                (println "If you want to go to main menu enter 2")
                (println "If you want to exit the program enter -1")
                (println "Try again...")
                (print "\u001B[0m")
                (Thread/sleep 1000)
                (song-menu)))))



(defn main-menu []
  (loop []
    (println)
    (println "======================================== START MENU ========================================")
    (println "Choose an option:")
    (println "1. Display all songs:")
    (println "2. For exiting the program enter -1")
    (println)
    (let [chosen-option (read-line)]
      (cond
        (= chosen-option "1") (display-all-songs)
        (= chosen-option "-1") (do (print "\u001B[35m") (println "Thank you.") (print "\u001B[0m") (System/exit 0))
        :else (do (print "\u001B[31m")
                  (println "Main menu will appear soon for you to try again")
                  (print "\u001B[0m")
                  (Thread/sleep 1000)
                  (recur))))))


;(main-menu)