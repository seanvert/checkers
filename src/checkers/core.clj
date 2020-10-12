(ns checkers.core
  (:use [clojure.set :only (difference)])
  (:gen-class))

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

(defn make-key
  ;; pega a x LETRA e o y NÙMERO
  [x y]
  (get-key-from-string (str x y)))

;; a notação usada nas funções será a mesma do xadrez, no formato:
;; <letra><número>
;; functions will use the same notation as chess,
;; <letter><number>

(defmacro item-maker
  [letter index]
  `(if (and ~letter ~index)
     (make-key ~letter ~index)))

(defn list-adjacent-squares
  ;; pega uma STRING no formato "<letra><número>"
  ;; ex: a1 b4 c2 d7
  [square]
  (let
      [
        letter (first square)
        index (subs square 1 2)
        int-letter (int letter)
        int-index (Integer/parseInt index)
       ]
    ;; código ascii da letra em int a == 97
    (def lower-letter (if (> (dec int-letter) 96)
                        (char (dec int-letter))
                        false))

    (def upper-letter (if (> (inc int-letter) 104)
                        false
                        (char (inc int-letter))))
    ;; index
    (def lower-index (if (> (dec int-index) 0)
                       (dec int-index)
                       false))

    (def upper-index (if (<= (inc int-index) 8)
                       (inc int-index)
                       false))
    ;TODO: depois olhar se existe uma função que faz produtos cartesianos
    (filter #(not (nil? %)) (list
                             (item-maker lower-letter lower-index)
                             (item-maker lower-letter upper-index)
                             (item-maker upper-letter upper-index)
                             (item-maker upper-letter lower-index)))))

(defn free-adjacent-squares
  [state square]
  ;; pegar o estado atual do tabuleiro
  ;; adicionar algo para checar peças inimigas capturáveis
  (filter (fn [x] (= (get state x) 0))
          (list-adjacent-squares square)))

(defn list-available-pieces
  [x]
  (if (= x "w")
    "white pieces"
    "black pieces"))

(defn list-legal-moves
  ;; start with regular stuff
  ;; then implement capturing
  [state x] ;; gets a square in 'a1' format
  (let [
        free (free-adjacent-squares state x)
        ]
    free))


(defn move-piece
  ;; gets a piece-pos and a new-pos in key format
  ;; this should run only if the moves are legal
  [state piece-pos new-pos]
  (let [
        piece-color (piece-pos state)
        inc-prop (if (= piece-color 1)
                   inc
                   #(+ % 2))
        dec-prop (if (= piece-color 1)
                   dec
                   #(- % 2))
        ]
  (update (update state new-pos inc-prop) piece-pos dec-prop)))

(defn someone-won
  [state]
  "todo")


(defn -main
  [in-state turn] ;; state
  (let [
        color-turn (if (= (mod turn 2) 0)
               "black" ;; black turn
               "white") ;; white turn
        ]

    )

  (let [
        move-from (get-key-from-string (read-line))
        move-to (get-key-from-string (read-line))
        out-state (move-piece in-state move-from move-to)
        ]
    (do (print out-state))
    ;; (-main out-state (inc turn)))
    ))

(defn get-move-input [
                      color ;; this is going to be either 'black' or 'white'
                      ]
  (loop [square (read-line)]
    ;; TODO make a macro out of this to use it on the white case too
    (if (and (= color "black")
             (= 2 ((get-key-from-string square) initial-table-state)))
      (print (list-legal-moves initial-table-state square))
      (recur (read-line))
      )))

;; DONE check turns
;; DONE validate entries and check for legal moves
;; TODO check if someone won
;; get another player position if no one won
;; calculate score
