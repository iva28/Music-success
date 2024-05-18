(ns parameter_experiments
  (:require [csv_load :refer [read-csv]]
            [conversion :refer [convert-to-float-list-of-lists]]
            [distance_functions :refer [calculate-euclidean-distance-csv]]
            [utility_functions :refer [all-songs-but-one
                                       sort-by-distance
                                       is-success
                                       count-hits-and-not-hits]]))

(defn calculate-distance-for-chosen-song
  [seq song]
  (map #(conj % (calculate-euclidean-distance-csv song %)) seq))

(def stones-csv (read-csv "src/songs/known_songs.csv"))
(def stones-songs (convert-to-float-list-of-lists (rest stones-csv)))

(count-hits-and-not-hits stones-songs)
;Imbalanced dataset
;Value 0 is dominant

(def first-test-list (subvec stones-songs 0 15))
(def second-test-list (subvec stones-songs 40 64))
(def third-test-list (subvec stones-songs 82 97))
(def forth-test-list (subvec stones-songs 150 165))
(def fifth-test-list (subvec stones-songs 230 245))
(def testing-lists [first-test-list second-test-list third-test-list forth-test-list fifth-test-list])

(defn calculate-correct-predictions
  [songs k]
  (let [correct-predictions-counter (atom 0)]
    ;(println "K" k)
    (doseq [song songs]
      (let [song-name (first song)
            distances-to-song (calculate-distance-for-chosen-song (all-songs-but-one stones-songs song-name) song-name)
            sorted-distances (sort-by-distance distances-to-song)
            top-3-closest (take k sorted-distances)
            real-value (last song)
            predicted-value (is-success top-3-closest)]
        ;(println "Real value:" real-value)
        ;(println "Predicted value:" predicted-value)
        (when (= real-value predicted-value)
          (swap! correct-predictions-counter inc)))
      ;(println @correct-predictions-counter)
      )
   (* (float (/ @correct-predictions-counter (count songs))) 100)))


;K=3
;(def average-k-3 (atom 0))
;(doseq [test-list testing-lists]
;  (swap! average-k-3 + (calculate-correct-predictions test-list 3)))
;(swap! average-k-3 / (count testing-lists))
;(println "Accuracy when k = 3: " @average-k-3)
;Accuracy when k = 3 is around 78%

;K = 5
;(def average-k-5 (atom 0))
;(doseq [test-list testing-lists]
;  (swap! average-k-5 + (calculate-correct-predictions test-list 5)))
;(swap! average-k-5 / (count testing-lists))
;(println "Accuracy when k = 5: " @average-k-5)
;Accuracy when k = 5 is around 78%
;No increment in accuracy score between k = 3 and k = 5
;k= 7 is chosen
