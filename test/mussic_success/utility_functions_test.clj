(ns mussic-success.utility-functions-test
  (:require [clojure.test :refer :all]
            [midje.sweet :refer :all]
            [utility_functions :refer :all]))
(deftest name-in-seq-test
  (testing "Name is in a sequence"
    (is (= (find-by-name ["Iva"] "Iva") true))))

(deftest name-not-in-seq-test
  (testing "Name is not in a sequence"
    (is (= (find-by-name ["Iva"] "Sasa") false))))

(deftest search-empty-seq-test
  (testing "Search for a name in empty sequence"
    (is (= (find-by-name nil "Sasa") false))))


(deftest sort-by-last-element-test
  (testing "Sort by last element in a sequence of one list"
    (is (= (sort-by-distance [1 2 4 0]) nil))))

(deftest sort-empty-list-test
  (testing "Sort by last element in an empty list"
    (is (= (sort-by-distance nil) nil))))
(deftest sort-list-of-list-test
  (testing "Sort by last element in a list of list"
    (is (= (sort-by-distance [[2 1] [9 9] [3 4]]) [[2 1] [3 4] [9 9]]))))
(deftest sort-list-of-list-with-words-test
  (testing "Sort by last element in a list of list with words"
    (is (= (sort-by-distance [["I" 4 1] [9 "Hello" 9] [3 0 4]])
           [["I" 4 1] [3 0 4] [9 "Hello" 9]]))))
(deftest find-same-songs-on-album-test
  (testing "Find songs on the same album"
    (is (= (find-same-songs-on-album [[1 "Album1"] [2 "Album2"] [3 "Album2"]] "Album2")
           [[2 "Album2"] [3 "Album2"]]))))
(deftest find-same-songs-on-empty-album-test
  (testing "Find songs on the same album with empty Album name"
    (is (= (find-same-songs-on-album [[1 "Album1"] [2 "Album2"] [3 "Album2"]] nil) nil))))
(deftest find-same-songs-on-empty-sequence-test
  (testing "Find songs on the same album with empty song sequence"
    (is (= (find-same-songs-on-album nil "Album1") nil))))

(deftest search-song-by-name-test
  (testing "Search song by its name"
    (is (= (search-by-name-return-song [["S1" 1] ["S2" 2]] "S1") [["S1" 1]]))))

(deftest search-song-by-name-no-songs-test
  (testing "Search song by its name, but there are no songs"
    (is (= (search-by-name-return-song nil "S1") nil))))

(deftest search-song-by-name-no-name-test
  (testing "Search song by its name, but there is no song name"
    (is (= (search-by-name-return-song [["S1" 1] ["S2" 2]] nil) nil))))

(deftest search-song-by-name-no-such-song-test
  (testing "Search song by its name, but there is no such song"
    (is (= (search-by-name-return-song [["S1" 1] ["S2" 2]] "S3") []))))

(deftest song-not-successful-test
  (testing "Song won't be successful"
    (is (= (is-success [[1 1] [0 1]]) 0))))

(deftest song-successful-test
  (testing "Song will be successful"
    (is (= (is-success [[1 1] [1 1]]) 1))))

(deftest song-success-no-songs-test
  (testing "Will song be successful, but no songs"
    (is (= (is-success nil) nil))))

;MIDJE TESTS

;(fact "Name is in a sequence"
;      (find-by-name ["Iva"] "Iva") => true)

;(fact "Name is not in a sequence"
;      (find-by-name ["Iva"] "Sasa") => false)

;(fact "Search for a name in empty sequence"
;      (find-by-name  nil "Sasa") => false)

;(fact "Sort by last element in a sequence of one list"
;      (sort-by-distance [1 2 4 0]) => nil)

;(fact "Sort by last element in an empty list"

;(fact "Sort by last element in a list of list"
;      (sort-by-distance [[2 1] [9 9] [3 4]]) => [[2 1] [3 4] [9 9]])
;      (sort-by-distance nil) => nil)

;(fact "Sort by last element in a list of list with words"
;      (sort-by-distance [["I" 4 1] [9 "Hello" 9] [3 0 4]]) => [["I" 4 1]  [3 0 4] [9 "Hello" 9]])

;(fact "Find songs on the same album"
;  (find-same-songs-on-album  [[1 "Album1"] [2 "Album2"] [3 "Album2"]] "Album2") =>
;      [[2 "Album2"] [3 "Album2"]])
;(fact "Find songs on the same album with empty Album name"
;      (find-same-songs-on-album [[1 "Album1"] [2 "Album2"] [3 "Album2"]] nil) => nil)
;(fact "Find songs on the same album with empty song sequence"
;(fact "Search song by it's name"
;      (search-by-name-return-song [["S1" 1] ["S2" 2]] "S1") => [["S1" 1]])
;      (find-same-songs-on-album nil "Album1") => nil)
;(fact "Search song by it's name, but there are no songs"
;      (search-by-name-return-song nil "S1") => nil)
;(fact "Search song by it's name, but there is no song name"
;      (search-by-name-return-song  [["S1" 1] ["S2" 2]] nil) => nil)

;(fact "Search song by it's name, but there is no such song"
;      (search-by-name-return-song  [["S1" 1] ["S2" 2]] "S3") => [])

;(fact "Song won't be successful"
;      (is-success [[1 1] [0 1]]) => 0)

;(fact "Song will be successful"
;      (is-success [[1 1] [1 1]]) => 1)

;(fact "Will song be successful, but no songs"
;      (is-success nil) => nil)



