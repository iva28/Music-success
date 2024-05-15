(ns csv_load)

(require '[clojure-csv.core :as csv]
          '[clojure.java.io :as io])

(defn read-csv
  [file_path]
  (with-open [file (io/reader file_path)]
    (-> file (slurp) (csv/parse-csv))))

(def stones-csv (read-csv "src/dataset/stones_analysis.csv"))

(take 1 stones-csv)
