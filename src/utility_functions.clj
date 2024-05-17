(ns utility_functions
  (:require [conversion :refer [convert-to-float-in-list]]))

(def example-list ["Example" 1 2 3])

(defn find-by-name
  [seq name]
  (= name (first seq)))

(find-by-name example-list (first example-list))

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

(def el1 [[1 0 5] [2 0 5] ["i" 1 ""] [1 8] [9 9 1 5]])
(is-success el1)


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
(sort-by-distance songs-trial)

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
   (println "Print number of hit songs in this dataset: " @count-hits)
   (println "Print number of songs that weren't hits in this dataset: "
            (- (count seq) @count-hits))
   (println "Hits make " (* (float (/ @count-hits (count seq))) 100))
   (println "Nonhits make " (* (float (/ (- (count seq) @count-hits) (count seq) )) 100))
   ))

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
  (nth seq (dec row)))

