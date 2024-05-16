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

;Modifying calculate-euclidean-distance function to adapt to stones csv data

(defn calculate-euclidean-distance-csv
  [v1 v2]
  (let [is-float? (fn [x] (number? x))
        sum-squared-diff (fn [x y]
                           (if (and (is-float? x) (is-float? y))
                             (* (- x y) (- x y))
                             0))]
    (Math/sqrt
      (reduce + (mapv sum-squared-diff v1 v2)))))

(def v1-mixed ["I" 1.0 3.0])
(def v2-mixed ["I" 4.0 5.0])
(calculate-euclidean-distance-csv v1-mixed v2-mixed)