(ns utility_functions)

(def example-list ["Example" 1 2 3])

(defn find-by-name
  [seq name]
  (= name (first seq)))

(find-by-name example-list (first example-list))