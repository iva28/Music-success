(ns parameter
  (:require [csv_load :refer [read-csv]]
            [conversion :refer [convert-to-float-list-of-lists]]
            [distance_functions :refer [calculate-euclidean-distance-csv]]
            [utility_functions :refer [all-songs-but-one sort-by-distance create-song-awards-map is-success]]))

(defn calculate-distance-for-chosen-song
  [seq song]
  (map #(conj % (calculate-euclidean-distance-csv song %)) seq))

(def stones-csv (read-csv "src/dataset/modified_stones.csv"))
(def stones-songs (convert-to-float-list-of-lists (rest stones-csv)))


;K=3
;one song
(def first-song (first stones-songs))
(print first-song)
(def distances-to-first-song (calculate-distance-for-chosen-song first-song (all-songs-but-one stones-songs first-song)))
(def distances-to-first-song (sort-by-distance distances-to-first-song))
(def top-3-closest (take 3 distances-to-first-song))

(def real-value (last first-song))
(def predicted-value (is-success top-3-closest))

(print real-value)
(print predicted-value)

;Testing with 10 songs k = 3
(def testing-songs (take 10 stones-songs))
(def testing-songs (create-song-awards-map testing-songs))
(def song-keys (keys testing-songs))
(get testing-songs (first song-keys))

(def right-predictions-3 (atom 0))
(doseq [key song-keys]
  (let [distances-to-ith-song (calculate-distance-for-chosen-song key (all-songs-but-one stones-songs key))
        distances-to-ith-song (sort-by-distance distances-to-ith-song)
        top-3-closest (take 3 distances-to-ith-song)
        predicted-value (is-success top-3-closest)]
    (if (= predicted-value (get testing-songs key))
      (swap! right-predictions-3 inc))))

(print @right-predictions-3)


;TAKE 222
(def ten-songs (take 10 stones-songs))

(doseq [song ten-songs]
  )
(def first-song (first stones-songs))
(print first-song)
(def distances-to-first-song (calculate-distance-for-chosen-song first-song (all-songs-but-one stones-songs first-song)))
(def distances-to-first-song (sort-by-distance distances-to-first-song))
(def top-3-closest (take 3 distances-to-first-song))

(def real-value (last first-song))
(def predicted-value (is-success top-3-closest))

(print (first (first ten-songs)))

(doseq [song ten-songs]
  (println (first song)))
(doseq [song ten-songs]
  (let [song-name (first song)
        distances-to-song (calculate-distance-for-chosen-song (all-songs-but-one stones-songs song-name) song-name)
        sorted-distances (sort-by-distance distances-to-song)
        top-3-closest (take 3 sorted-distances)
        real-value (last song)
        predicted-value (is-success top-3-closest)]
    (println "Real value:" real-value)
    (println "Predicted value:" predicted-value)))

(defn calculate-correct-predictions
  [songs k]
  (doseq [song songs]
    (let [song-name (first song)
          distances-to-song (calculate-distance-for-chosen-song (all-songs-but-one stones-songs song-name) song-name)
          sorted-distances (sort-by-distance distances-to-song)
          top-3-closest (take k sorted-distances)
          real-value (last song)
          predicted-value (is-success top-3-closest)]
      (println "Real value:" real-value)
      (println "Predicted value:" predicted-value))) )


(calculate-correct-predictions ten-songs 3)


(defn calculate-correct-predictions
  [songs k]
  (let [correct-predictions-counter (atom 0)]
    (doseq [song songs]
      (let [song-name (first song)
            distances-to-song (calculate-distance-for-chosen-song (all-songs-but-one stones-songs song-name) song-name)
            sorted-distances (sort-by-distance distances-to-song)
            top-3-closest (take k sorted-distances)
            real-value (last song)
            predicted-value (is-success top-3-closest)]
        (println "Real value:" real-value)
        (println "Predicted value:" predicted-value)
        (when (= real-value predicted-value)
          (swap! correct-predictions-counter inc))))
    @correct-predictions-counter))

(calculate-correct-predictions ten-songs 3)

