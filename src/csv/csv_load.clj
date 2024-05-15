(ns csv_load)

(require '[clojure-csv.core :as csv]
          '[clojure.java.io :as io])

(defn read-csv
  [file_path]
  (with-open [file (io/reader file_path)]
    (-> file (slurp) (csv/parse-csv))))

(def stones-csv (read-csv "src/dataset/stones_analysis.csv"))

(take 1 stones-csv)

(print (count stones-csv))


;Column names
(def csv-column-names (vec (first stones-csv)))
(print csv-column-names)
(count csv-column-names)

(defn index-of-element-by-name
  [seq name]
   (.indexOf seq name))

(def first-award-column (index-of-element-by-name csv-column-names "British charts"))
(print first-award-column)

