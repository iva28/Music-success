(ns mussic-success.conversion-test
  (:require [clojure.test :refer :all]
            [conversion :refer :all]))

(deftest convert-to-float-int-test
  (testing "Testing converting integer value to float"
    (is (= (convert-to-float 1) 1.0))))

(deftest convert-to-float-string-test
  (testing "Testing converting integer value to float"
    (is (= (convert-to-float "1") 1.0))))

(deftest convert-to-float-in-list-test
  (testing "Converting elements of a list from third to penultimate index"
    (let [seq ["a" "a" "1" "2.0" "3.3" "1" "3"]
          result (convert-to-float-in-list seq)
          expected ["a" "a" 1 2.0 (Float/parseFloat "3.3") 1.0 3]
          compare-results (map = result expected)]
      (is (= (count seq) (count result)))
      (is (every? true? compare-results)))))

(deftest convert-to-float-in-list-of-lists-test
  (testing "Converting elements of a list of lists from third to penultimate index"
    (let [seq [["a" "a" "1" "2.0" "3.3" "1" "3"] ["a" "b" "2" "3.0" "3.3" "1" "4"]]
          result (convert-to-float-list-of-lists seq)
          expected [["a" "a" 1 2.0 (Float/parseFloat "3.3") 1.0 3]
                    ["a" "b" 2 3.0 (Float/parseFloat "3.3") 1.0 4]]
          compare-results (map = result expected)]
      (is (= (count seq) (count result)))
      (is (every? true? compare-results)))))