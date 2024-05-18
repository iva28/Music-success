(ns utility_functions
  (:require [conversion :refer [convert-to-float-in-list]]))

;(def example-list ["Example" 1 2 3])

(defn find-by-name
  [seq name]
  (= name (first seq)))

;(defn is-success
;  [seq]
;  (let [counts (reduce (fn [acc el]
;                         (if (= (last el) 0)
;                           (update acc :zeros inc)
;                           (update acc :ones inc)))
;                       {:zeros 0 :ones 0}
;                       seq)]
;    (if (> (:ones counts) (:zeros counts))
;      1
;      0)))

;Changed is-success because calculations are appended on the end
(defn is-success
  [seq]
  (let [counts (reduce (fn [acc el]
                         (if (= (nth el (- (count el) 2)) 0)
                           (update acc :zeros inc)
                           (update acc :ones inc)))
                       {:zeros 0 :ones 0}
                       seq)]
    (if (> (:ones counts) (:zeros counts))
      1
      0)))


(defn all-songs-but-one
  [seq song]
  ;(remove #(find-by-name % song) seq)
  (convert-to-float-in-list(remove #(find-by-name % song) seq)))

(def songs-trial [["S1" 2 3 ] ["S3" 1] ["S4" 2]])
(def exclude-trial (all-songs-but-one songs-trial "S1"))
;(print exclude-trial)

(defn sort-by-distance
  [seq]
  (sort-by last seq))
;(sort-by-distance songs-trial)

(defn create-song-awards-map
  [seq]
  (reduce (fn [acc el]
            (assoc acc (first el) (last el)))
          {}
          seq))

(defn print-sequence
  [seq]
  (doseq [el seq]
    (println el)))

(defn count-hits-and-not-hits
  [seq]
  (let [count-hits (atom 0)]
    (doseq [song seq]
      (if (= 1 (last song))
        (swap! count-hits inc)))
    {:hits @count-hits
     :non-hits (- (count seq) @count-hits)}))


(defn format-song-row-number [songs]
  (map
    (fn [[index song]]
      (str index ". " song))
    songs))

(defn search-by-name-return-song
  [seq song-name]
  (filter #(= song-name (first %)) seq))

(defn search-by-row-number
  [seq row]
  (try
    (let [index (dec (Integer/parseInt row))]
      (if (and (>= index 0) (< index (count seq)))
        (nth seq index)
        (throw (Exception. "Index out of range"))))
    (catch Exception e
      (println "Error"))))

(defn find-album-of-song-by-row
  [seq row]
  (let [song (search-by-row-number seq row)]
    (nth song 2)))

(defn format-print-song
  [song]
  (println "Title:" (first song) "            Album name:" (second song)
           "            Songwriter:" (nth song 3) "       Lead vocalist:" (nth song 4)))

(defn find-same-songs-on-album
  [seq album]
  (filter #(= (second %) album) seq))

(defn print-just-song
  [songs]
  (doseq [song songs]
    (println (first song))))

(defn prepare-unknown-dataset
  [seq]
  (map #(vec (butlast %)) seq))