(ns shuffle_functions)
(def first-list [1 2 3])
(def second-list [])

(defn add-random-element-from-list
  [first-list second-list]
  (let [list-size (count first-list)
        random-index (shuffle (range list-size))
        random-lists (map #(nth first-list %) random-index)]
    (apply conj second-list random-lists)))

(def updated-second (add-random-element-from-list first-list second-list))
;(print updated-second)

;Trial with onther values than numbers
(def first-list-words [1 "Iva" 3 7 "Sasa"])
(def second-list-words [])

(def updated-second-words
  (add-random-element-from-list first-list-words second-list-words))
;(print updated-second-words)

;Trial with list of lists
(def first-list-list [[1 2 3] ["Iva" 7] [5 6 7] ["Ana" 2 10]])
(def second-list-list [[]])
(def updated-second-list-list (add-random-element-from-list first-list-words []))
;(print updated-second-list-list)

(defn add-random-element-from-list-of-list
  [list-of-lists]
  (let [shuffled-index (shuffle (range (count list-of-lists)))]
    (mapv #(nth list-of-lists %) shuffled-index)))
(add-random-element-from-list-of-list first-list-list)


