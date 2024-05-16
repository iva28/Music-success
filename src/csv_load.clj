(ns csv_load
  (:require [clojure-csv.core :as csv]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(defn read-csv
  [file_path]
  (with-open [file (io/reader file_path)]
    (-> file (slurp) (csv/parse-csv))))

(def stones-csv (read-csv "src/dataset/stones_analysis.csv"))
(take 1 stones-csv)
(print (count stones-csv))

;Column names
(def csv-column-names (vec (first stones-csv)))
(def songs-only (rest stones-csv))
(print csv-column-names)
(count csv-column-names)

(defn index-of-element-by-name
  [seq name]
   (.indexOf seq name))

(def first-award-column (index-of-element-by-name csv-column-names "British charts"))
(print first-award-column)

;columns for awards
(subvec (second stones-csv) first-award-column (count csv-column-names))
;check if first song has received some reward
(some #(not= % "No") (subvec (second stones-csv) first-award-column (count csv-column-names)))

(def updated-first-song (if (some #(not= % "No") (subvec (second stones-csv) first-award-column (count csv-column-names)))
                          (conj (second stones-csv) 1)
                          (conj (second stones-csv) 0)))
(print updated-first-song)

(defn check-for-award
  [seq]
  (some #(not= % "No") (subvec seq first-award-column (count csv-column-names))))

(defn add-award-flag-for-all
  [songs]
  (map #(if (check-for-award %)
          (conj % "1")
          (conj % "0")) songs))

(def updated-songs-only (add-award-flag-for-all songs-only))
(print (count updated-songs-only))
(take 1 updated-songs-only)
(print first-award-column)
(print (count csv-column-names))
(print (count (first updated-songs-only)))

(subvec (first updated-songs-only) 0 first-award-column)
(concat (subvec (first updated-songs-only) 0 first-award-column) (last (first updated-songs-only)))
(concat (subvec (second updated-songs-only) 0 first-award-column) (last (second updated-songs-only)))

(defn songs-only-no-awards-columns
  [songs first-award-column]
  (map #(concat (subvec % 0 first-award-column) [(last %)]) songs))

(def modified-songs (songs-only-no-awards-columns updated-songs-only first-award-column))
(print (count modified-songs))
(print (count (first modified-songs)))
(println (first modified-songs))

(print (count csv-column-names))
(print first-award-column)

(defn modify-column-names
  [songs first-award-column]
  (map #(concat (subvec %  0 first-award-column) [(last %)]) songs))

(subvec csv-column-names 0 first-award-column)
(def modified-column-names (conj (subvec csv-column-names 0 first-award-column) "Won award"))
(print modified-column-names)

;SAVING NEW CSV
(defn save-modified-songs-csv [songs column-names filename]
  (with-open [writer (io/writer filename)]
    (.write writer (str/join "," column-names))
    (.write writer "\n")
    (doseq [song songs]
      (.write writer (str/join "," song))
      (.write writer "\n"))))

(save-modified-songs-csv modified-songs modified-column-names "src/dataset/modified_stones.csv")