(ns mussic-success.csv-load-test
  (:require [clojure.test :refer :all]
            [csv_load :refer :all]))

;(deftest check-if-number-nil-test
;   (testing "Check if nil is number"
;     (is (= nil (check-if-number nil))))))

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


