(ns parameter
  (:require [csv_load :refer [read-csv]]
            [conversion :refer [convert-to-float-list-of-lists]]
            [distance_functions :refer [calculate-euclidean-distance-csv]]
            [utility_functions :refer [all-songs-but-one
                                       sort-by-distance
                                       create-song-awards-map
                                       is-success
                                       find-by-name
                                       print-sequence]]))

(defn calculate-distance-for-chosen-song
  [seq song]
  (map #(conj % (calculate-euclidean-distance-csv song %)) seq))

(def stones-csv (read-csv "src/dataset/modified_stones.csv"))
(def stones-songs (convert-to-float-list-of-lists (rest stones-csv)))


;K = 3
(def first-test-list (subvec stones-songs 0 30))
(def second-test-list (subvec stones-songs 40 70))
(def third-test-list (subvec stones-songs 82 112))
(def forth-test-list (subvec stones-songs 150 180))
(def fifth-test-list (subvec stones-songs 230 260))
(def testing-lists [first-test-list second-test-list third-test-list forth-test-list fifth-test-list])

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
        ;(println "Real value:" real-value)
        ;(println "Predicted value:" predicted-value)
        (when (= real-value predicted-value)
          (swap! correct-predictions-counter inc))))
   (* (float (/ @correct-predictions-counter (count songs))) 100)))

;second trial
(println (calculate-correct-predictions first-test-list 3))
(println (calculate-correct-predictions first-test-list 5))
(println (calculate-correct-predictions first-test-list 7))

(def average-k-3 (atom 0))
(doseq [test-list testing-lists]
  (swap! average-k-3 + (calculate-correct-predictions test-list 3)))
(swap! average-k-3 / (count testing-lists))
(println "Accuracy when k = 3: " @average-k-3)

;K = 5
(def average-k-5 (atom 0))
(doseq [test-list testing-lists]
  (swap! average-k-5 + (calculate-correct-predictions test-list 5)))
(swap! average-k-5 / (count testing-lists))
(println "Accuracy when k = 5: " @average-k-5)

;K = 7
(def average-k-7 (atom 0))
(doseq [test-list testing-lists]
  (swap! average-k-7 + (calculate-correct-predictions test-list 7)))
(swap! average-k-7 / (count testing-lists))
(println "Accuracy when k = 7: " @average-k-7)

;K = 9
(def average-k-9 (atom 0))
(doseq [test-list testing-lists]
  (swap! average-k-9 + (calculate-correct-predictions test-list 9)))
(swap! average-k-9 / (count testing-lists))
(println "Accuracy when k = 7: " @average-k-9)

;
;k = 5
(def f5 (calculate-correct-predictions first-test-list 5))
(def s5 (calculate-correct-predictions second-test-list 5))
(def t5 (calculate-correct-predictions third-test-list 5))
(def forth5 (calculate-correct-predictions forth-test-list 5))
(def fiv5 (calculate-correct-predictions fifth-test-list 5))

(print (float (/ (reduce + [f5 s5 t5 forth5 fiv5]) 5)))

;k = 7

(def f5 (calculate-correct-predictions first-test-list 7))
(def s5 (calculate-correct-predictions second-test-list 7))
(def t5 (calculate-correct-predictions third-test-list 7))
(def forth5 (calculate-correct-predictions forth-test-list 7))
(def fiv5 (calculate-correct-predictions fifth-test-list 7))

(print (float (/ (reduce + [f5 s5 t5 forth5 fiv5]) 5)))

;k = 9
(def f5 (calculate-correct-predictions first-test-list 9))
(def s5 (calculate-correct-predictions second-test-list 9))
(def t5 (calculate-correct-predictions third-test-list 9))
(def forth5 (calculate-correct-predictions forth-test-list 9))
(def fiv5 (calculate-correct-predictions fifth-test-list 9))

(print (float (/ (reduce + [f5 s5 t5 forth5 fiv5]) 5)))

(def f5 (calculate-correct-predictions first-test-list 3))
(def s5 (calculate-correct-predictions second-test-list 3))
(def t5 (calculate-correct-predictions third-test-list 3))
(def forth5 (calculate-correct-predictions forth-test-list 3))
(def fiv5 (calculate-correct-predictions fifth-test-list 3))

(print (float (/ (reduce + [f5 s5 t5 forth5 fiv5]) 5)))
;

(defn calculate-average-accuracy
  [test-lists k]
  (let [total (reduce + (for [test-list test-lists]
                          (calculate-correct-predictions test-list k)))]
    (float (/ total (count test-lists)))))

(println (calculate-average-accuracy testing-lists 0))

;Finding bug
(def first-song (first stones-songs))
(print first-song)
(print (first first-song))
;Testing all songs but one - works fine
(def excluding-first (all-songs-but-one stones-songs (first first-song)))

;Testing calculating distance - OK
(def distances-first (calculate-distance-for-chosen-song excluding-first first-song))
(print-sequence distances-first)

;Testing sorting - OK

(def sorted-distances-first (sort-by-distance distances-first))
(print-sequence sorted-distances-first)

;Testing taking 3 closest
(def three-closest (take 0 sorted-distances-first))
(print-sequence three-closest)

(def predicted-value (is-success three-closest))
(print predicted-value)
(def real-value (last first-song))
(print real-value)

;Inaccurate for k = 3
; K = 5
(def five-closest (take 5 sorted-distances-first))
(print-sequence five-closest)
(def predicted-value-5 (is-success five-closest))
(print predicted-value-5)

; k = 7
(def seven-closest (take 7 sorted-distances-first))
(print (is-success seven-closest))

;k = 9
(def nine-closest (take 9 sorted-distances-first))
(print (is-success nine-closest))

;SECOND SONG

(def second-song (second stones-songs))
(print second-song)
(print (first second-song))
(def excluding-first (all-songs-but-one stones-songs (first second-song)))
(def distances-first (calculate-distance-for-chosen-song excluding-first second-song))
(def sorted-distances-first (sort-by-distance distances-first))


;K = 3
(def three-closest (take 3 sorted-distances-first))
(print-sequence three-closest)

(def predicted-value (is-success three-closest))
(print predicted-value)
(def real-value (last first-song))
(print real-value)

;Testing calculating euclidian distance
(print first-song)
(print second-song)

(def distance-first-second (calculate-euclidean-distance-csv first-song second-song))
(print distance-first-second)

