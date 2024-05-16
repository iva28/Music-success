(ns distance_functions)

(defn calculate-euclidean-distance
  [v1 v2]
  (let [sum-squared-diff (fn [x y] (* (x - y) (x - y)))]
    (Math/sqrt
      (reduce + (mapv sum-squared-diff v1 v2)))))

(def v1 [1 3])
(def v2 [4 5])

;(print ())