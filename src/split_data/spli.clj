(def first-list [1 2 3])

(def second-list [])

(defn add-random-element-from-list
  [first-list second-list]
  (let [list-size (count first-list)
        random-index (shuffle (range list-size))
        random-lists (map #(nth first-list %) random-index)]
    (apply conj second-list random-lists)))

(def updated-second (add-random-element-from-list first-list second-list))
(print updated-second)