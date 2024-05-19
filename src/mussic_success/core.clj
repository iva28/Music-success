(ns mussic-success.core
  (:gen-class)
  (:require [menu]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (loop []
    (println "\n\nPlease enter your name: ")
    (flush)
    (let [name (clojure.string/trim (read-line))]
      (if (empty? name)
        (recur)
        (do
          (print "\u001B[34m")
          (println (str "\nHello, " name "!\n"))
          (print "\u001B[0m")
          (menu/main-menu)))))
  )

(-main)