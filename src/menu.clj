(ns menu )

(defn main-menu []
  (println "Choose an option:")
  (println "1. Display all songs:")
  (let [chosen-option (read-line)]
    (case chosen-option
      "1" (println "Successfully chosen option 1.")
      (do
        (println "Unknown option")
        (main-menu)))))

(main-menu)