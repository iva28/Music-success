(ns distance_functions)

(defn calculate-euclidean-distance
  [v1 v2]
  (let [sum-squared-diff (fn [x y] (* (- x y) (- x y)))]
    (Math/sqrt
      (reduce + (mapv sum-squared-diff v1 v2)))))

(def v1 [1 3])
(def v2 [4 5])
;Check for same vectors
(print (calculate-euclidean-distance v1 v1))
;Check for different vectors with 2 elements
(print (calculate-euclidean-distance v1 v2))

(def v3 [1 2 3])
(def v4 [3 5 6])
(print (calculate-euclidean-distance v3 v4))