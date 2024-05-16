(def first_list [1 2 3])

(def second_list [])

(defn add-random-element-from-list
  [first-list second-list]
  (let [random-index (rand-int (count second-list))
        random-list (nth second-list random-index)]
    (conj first-list random-list)))

