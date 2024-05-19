(ns mussic-success.csv-load-test
  (:require [clojure.test :refer :all]
            [csv_load :refer :all]))


(deftest check-for-award-nil-test
  (testing "Checking for an award when the award is nil"
    (is (= nil (check-for-award nil)))))

(deftest check-for-award-with-awards-test
  (testing "Check for award when awards are present"
    (is (= (check-for-award (vec(repeat 24 "Yes"))) true))))

(deftest check-for-award-no-awards-test
  (testing "Check for award when no awards are present"
    (is (= (check-for-award (vec (repeat 24 "No"))) nil))))

(deftest check-for-award-with-at-least-one-test
  (testing "Check for award when awards are present"
    (is (= (check-for-award (vec (concat (repeat 23 "No") ["Yes"]))) true))))

(deftest index-of-element-by-name-right-check
  (testing "Check if it returns the index of a given name"
    (is (= (index-of-element-by-name ["A" "B" "C"] "A") 0))))
(deftest index-of-element-by-name-nil-check
  (testing "Check if it returns the index of a given name"
    (is (= (index-of-element-by-name ["A" "B" "C"] nil) nil))))
(deftest index-of-element-by-name-songs-nil-check
  (testing "Check if it returns the index of a given name"
    (is (= (index-of-element-by-name nil "A") nil))))

