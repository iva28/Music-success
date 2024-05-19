(ns mussic-success.core
  (:gen-class)
  (:require [menu]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Please enter your name: ")
  (flush)
  (let [name (read-line)]
    (println (str "\nHello, " name "!\n"))
    (menu/main-menu)))

(-main)