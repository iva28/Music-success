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

;Trial with onther values than numbers

(def first-list-words [1 "Iva" 3 7 "Sasa"])
(def second-list-words [])

(def updated-second-words
  (add-random-element-from-list first-list-words second-list-words))
(print updated-second-words)
