(ns mussic-success.distance-functions-test
  (:require [clojure.test :refer :all]
            [distance_functions :refer :all]))
(deftest check-if-number-float-test
  (testing "Check if float is number"
    (is (= true (check-if-number 1.0)))))

(deftest check-if-number-int-test
  (testing "Check if int is number"
    (is (= true (check-if-number 1)))))

(deftest check-if-number-string-test
  (testing "Check if string is number"
    (is (= false (check-if-number "1")))))

(deftest check-sum-squared-diff-nil-test
  (testing "Testing what happens when in sum squared difference both vectors are nil"
    (is (= 0 (sum-squared-diff nil nil)))))

(deftest check-sum-squared-diff-with-strings-tests
  (testing "Testing what happens when in sum squared difference vectors have strings"
    (is (= 0 (sum-squared-diff [1 1] "1")))))

(deftest check-sum-squared-diff-regular-tests
  (testing "Testing what happens when in sum squared difference both vectors are in right format"
    (is (= 4 (sum-squared-diff 4 2)))))

(deftest calculate-euclidean-distance-right-test
  (testing "Testing euclidean distance calculations"
    (is (= (float 5) (calculate-euclidean-distance [1 3] [5 6])))))

(deftest calculate-euclidean-distance-unequal-sizes-test
  (testing "Testing euclidean distance calculations when vectors' sizes don't match"
    (is (= 0 (calculate-euclidean-distance [1 3] [5 0 9 9 6])))))