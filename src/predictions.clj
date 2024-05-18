(ns predictions
  (:require [clojure.string :as str]
            [parameter_experiments :refer [calculate-distance-for-chosen-song]]))


;Provided with a name of a song
(defn predict-songs-success-by-name
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
  [seq song]
  (let [distances-to-song (calculate-distance-for-chosen-song seq song)
        sorted-distances (utility_functions/sort-by-distance distances-to-song)
        top-7-closest (take 7 sorted-distances)
        song-names (map first top-7-closest)
         ]
    (println (str/join ",     " (take 3 song-names)))))



