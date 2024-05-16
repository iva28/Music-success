(ns utility_functions
  (:require [conversion :refer [convert-to-float-in-list]]))

(def example-list ["Example" 1 2 3])

(defn find-by-name
  [seq name]
  (= name (first seq)))

(find-by-name example-list (first example-list))

(defn is-success
  [seq]
  (let [counts (reduce (fn [acc el]
                         (if (= (last el) 0)
                           (update acc :zeros inc)
                           (update acc :ones inc)))
                       {:zeros 0 :ones 0}
                       seq)]
    (> (:ones counts) (:zeros counts))))

(def el1 [[1 0] [2 0] ["i" 1] [1] [9 9 1]])
(is-success el1)


(defn all-songs-but-one
  [seq song]
  ;(remove #(find-by-name % song) seq)
  (convert-to-float-in-list(remove #(find-by-name % song) seq)))

(def songs-trial [["S1" 2 3 ] ["S3" 1] ["S4" 2]])
(def exclude-trial (all-songs-but-one songs-trial "S1"))
(print exclude-trial)

(defn sort-by-distance
  [seq]
  (sort-by last seq))

(sort-by-distance songs-trial)
