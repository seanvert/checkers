(ns checkers.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn mainloop
  []
  "todo")

(def initial-table-state
  {
   ;; white pieces
   :a1 1
   :b2 1
   :c1 1
   :d2 1
   :e1 1
   :f2 1
   :g1 1
   :h2 1
   :a3 1
   :c3 1
   :e3 1
   :g3 1
   ;; empty squares
   :a5 0
   :b4 0
   :c5 0
   :d4 0
   :e5 0
   :f4 0
   :g5 0
   :h4 0
   ;; black pieces
   :a7 2
   :b8 2
   :b6 2
   :c7 2
   :d8 2
   :d6 2
   :e7 2
   :f8 2
   :f6 2
   :g7 2
   :h8 2
   :h6 2
   })

(defn get-key-from-string
  [entry]
  (read-string (str ":" entry)))

;; padrão que eu vou seguir para montar o esquema dos quadrados
;; adjacentes
;; <letra><dígito>
;; casas adjacentes:
;; <letra + 1><número + 1>
;; <letra><número +- 1>
;; <letra - 1><número - 1>

(defn list-adjacent-squares
  ;; pega uma string no formato "<letra><número>"
  ;; ex: a1 b4 c2 d7
  [square]
  (let [letter (first square)
        index (subs square 1 2)
        int-letter (int letter)
        int-index (Integer/parseInt index)
        make-key #(get-key-from-string (str %1 %2)) ;; pega a %1 letra e o %2 número
        ]
    ;; código ascii da letra em int a == 97
    (def lower-letter (if (> (dec int-letter) 96)
                        (char (dec int-letter))
                        \j))
    (def upper-letter (char (inc int-letter)))
    ;; index
    (def lower-index (dec int-index))
    (def upper-index (inc int-index))
    (list
     (make-key lower-letter lower-index)
     (make-key lower-letter upper-index)
     (make-key upper-letter upper-index)
     (make-key upper-letter lower-index)
     )))

(list-adjacent-squares "d3")

(defn check-adjacent-squares-for-pieces
  [square]
  ;; pegar o estado atual do tabuleiro
  (map (fn [x] (get initial-table-state x)) (list-adjacent-squares square))
  )

(check-adjacent-squares-for-pieces "b8")
(defn list-available-pieces
  [x]
  (if (= x "w")
    "white pieces"
    "black pieces"))


(defn list-legal-moves
  ;; start with regular stuff
  ;; then implement capturing
  [x] ;; gets a square in 'a1' format
  (let [adjacent (list-adjacent-squares x)]
    ))
