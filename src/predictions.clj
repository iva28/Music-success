(ns predictions
  (:require [clojure.string :as str]
            [parameter_experiments :refer [calculate-distance-for-chosen-song]]))


;Provided with a name of a song
(defn predict-songs-success-by-name
  "Predicting song's success.
  First the distance between chosen song and all others is calculated and top 7 are picked.
  Based on those 7 chosen songs, song will be classified into hit or not hit, by majority vote."
  [seq song]
  (let [distances-to-song  (calculate-distance-for-chosen-song seq song)
        sorted-distances (utility_functions/sort-by-distance distances-to-song)
        top-7-closest (take 7 sorted-distances)
        predicted-value (utility_functions/is-success top-7-closest)]
    ;(doseq [song top-7-closest]
    ;  (println song))
    (if (= predicted-value 1)
      "Song will be hit!"
      "Song won't be hit :(")
    ))


(defn top-3-closest-songs-by-name
  "Calculating distances between chosen song and all other songs.
  Then, the distances are sorted and top 3 are picked out as most similar."
  [seq song]
  (let [distances-to-song (calculate-distance-for-chosen-song seq song)
        sorted-distances (utility_functions/sort-by-distance distances-to-song)
        top-7-closest (take 7 sorted-distances)
        song-names (map first top-7-closest)
         ]
    (println (str/join ",     " (take 3 song-names)))))



