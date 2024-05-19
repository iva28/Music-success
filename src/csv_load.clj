(ns csv_load
  (:require [clojure-csv.core :as csv]
            [clojure.data.csv :as csv2]
            [clojure.java.io :as io]
            [clojure.string :as str]))


(defn read-csv
  "Reading csv file"
  [file_path]
  (with-open [file (io/reader file_path)]
    (-> file (slurp) (csv/parse-csv))))

(def stones-csv (read-csv "src/dataset/stones_analysis.csv"))

;Column names
(def csv-column-names (vec (first stones-csv)))
(def songs-only (rest stones-csv))


(defn index-of-element-by-name
  "Get index of an element in a sequence. Used for getting index of a column name"
  [seq name]
  (if (or (nil? seq) (nil? name))
    nil
    (.indexOf seq name)))

(def first-award-column (index-of-element-by-name csv-column-names "British charts"))

(def updated-first-song (if (some #(not= % "No") (subvec (first songs-only) first-award-column (count csv-column-names)))
                          (conj (first songs-only) 1)
                          (conj (first songs-only) 0)))

(def song-has-award (nth songs-only 6))
(def updated-song-has-award (if (some #(not= % "No") (subvec song-has-award first-award-column (count csv-column-names)))
                          (conj song-has-award 1)
                          (conj song-has-award 0)))

(defn check-for-award
  "Checking if song has won any award.
  If value is different to No in any award columns that it has."
  [seq]
  (if (nil? seq)
    nil
    (some #(not= % "No") (subvec seq first-award-column (count csv-column-names)))))


(defn add-award-flag-for-all
  "Flagging songs that have won an award with 1"
  [songs]
  (map #(if (check-for-award %)
          (conj % "1")
          (conj % "0")) songs))

(def updated-songs-only (add-award-flag-for-all songs-only))

(defn songs-only-no-awards-columns
  "Removing award columns"
  [songs first-award-column]
  (map #(concat (subvec % 0 first-award-column) [(last %)]) songs))

(def modified-songs (songs-only-no-awards-columns updated-songs-only first-award-column))

(defn modify-column-names
  "Modifying column names"
  [songs first-award-column]
  (map #(concat (subvec %  0 first-award-column) [(last %)]) songs))

;(subvec csv-column-names 0 first-award-column)
(def modified-column-names (conj (subvec csv-column-names 0 first-award-column) "Won award"))



;SAVING NEW CSV
(defn save-modified-songs-csv [songs column-names filename]
  "Saving modified csv file"
  (with-open [writer (io/writer filename)]
    (csv2/write-csv writer (conj (list* songs) column-names))))
(save-modified-songs-csv modified-songs modified-column-names "src/dataset/modified_stones.csv")

;Load just song names
(defn read-songs-only [file]
  "Reading only name of songs from csv"
  (with-open [reader (io/reader file)]
    (mapv
      (fn [line]
        (let [parts (str/split line #",")]
          (first parts)))
      (line-seq reader))))
(def songs-only (read-songs-only "src/dataset/shuffled_songs.csv"))

(defn add-index-to-songs [songs]
  "Adding indexes to songs"
  (map-indexed
    (fn [index song-name]
      [index song-name])
    songs))

(def modified-songs (list (add-index-to-songs songs-only)))

(defn modified-songs-and-index
  "Concatenating songs with indexes"
  [file]
  (rest (vec (add-index-to-songs (with-open [reader (io/reader file)]
    (mapv
      (fn [line]
        (let [parts (str/split line #",")]
          (first parts)))
      (line-seq reader)))))))

;(def result-songs-indexes (modified-songs-and-index "src/dataset/shuffled_songs.csv"))


