(ns mussic-success.shuffle-function-test
  (:require [clojure.test :refer :all]
            [shuffle_functions :refer :all]))

(deftest shuffle-functions-right-test
  (testing "Check if sequence two is a shuffled sequence of sequence one"
    (let [seq1 [1 2 3]
          seq2 (add-random-element-from-list seq1 [])]
      (is (= (count seq1) (count seq2)))
      (is (= (set seq1) (set seq2)))
      (is (not= seq1 seq2)))))

