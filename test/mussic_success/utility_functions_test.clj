(ns mussic-success.utility-functions-test
  (:require [midje.sweet :refer :all]
            [utility_functions :refer :all]))

(fact "Name is in a sequence"
      (find-by-name ["Iva"] "Iva") => true)

(fact "Name is not in a sequence"
      (find-by-name ["Iva"] "Sasa") => false)

(fact "Search for a name in empty sequence"
      (find-by-name  nil "Sasa") => false)

(fact "Sort by last element in a sequence of one list"
      (sort-by-distance [1 2 4 0]) => nil)

(fact "Sort by last element in an empty list"
      (sort-by-distance nil) => nil)

(fact "Sort by last element in a list of list"
      (sort-by-distance [[2 1] [9 9] [3 4]]) => [[2 1] [3 4] [9 9]])

(fact "Sort by last element in a list of list with words"
      (sort-by-distance [["I" 4 1] [9 "Hello" 9] [3 0 4]]) => [["I" 4 1]  [3 0 4] [9 "Hello" 9]])

(fact "Find songs on the same album"
  (find-same-songs-on-album  [[1 "Album1"] [2 "Album2"] [3 "Album2"]] "Album2") =>
      [[2 "Album2"] [3 "Album2"]])

(fact "Find songs on the same album with empty Album name"
      (find-same-songs-on-album [[1 "Album1"] [2 "Album2"] [3 "Album2"]] nil) => nil)

(fact "Find songs on the same album with empty song sequence"
      (find-same-songs-on-album nil "Album1") => nil)

(fact "Search song by it's name"
      (search-by-name-return-song [["S1" 1] ["S2" 2]] "S1") => [["S1" 1]])

(fact "Search song by it's name, but there are no songs"
      (search-by-name-return-song nil "S1") => nil)

(fact "Search song by it's name, but there is no song name"
      (search-by-name-return-song  [["S1" 1] ["S2" 2]] nil) => nil)

(fact "Search song by it's name, but there is no such song"
      (search-by-name-return-song  [["S1" 1] ["S2" 2]] "S3") => [])

(fact "Song won't be successful"
      (is-success [[1 1] [0 1]]) => 0)

(fact "Song will be successful"
      (is-success [[1 1] [1 1]]) => 1)

(fact "Will song be successful, but no songs"
      (is-success nil) => nil)

