(ns checkers.core
  (:use [clojure.set :only (difference)])
  (:gen-class))

(defn -main
  ;; table state
  ;; gets a position
  ;; check if piece is able to move
  ;; returns a hashset of valid piece moves
  ;; get a move and create new state
  ;; returns current table state
  ;; check if someone won
  ;; get another player position if no one won
  ;; calculate score
  [& args]
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


;; a notação usada nas funções será a mesma do xadrez, no formato:
;; <letra><número>
;; functions will use the same notation as chess,
;; <letter><number>

(defn list-adjacent-squares
  ;; pega uma string no formato "<letra><número>"
  ;TODO: tirar o nil do hash de saída
  ;; ex: a1 b4 c2 d7
  [square]
  (let
      [
        letter (first square)
        index (subs square 1 2)
        int-letter (int letter)
        int-index (Integer/parseInt index)
        make-key #(get-key-from-string (str %1 %2)) ;; pega a %1 letra e o %2 número
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
    ;; (difference #{nil}
                (hash-set
                 (if (and lower-letter lower-index)
                   (make-key lower-letter lower-index))

                 (if (and lower-letter upper-index)
                   (make-key lower-letter upper-index))

                 (if (and upper-letter upper-index)
                   (make-key upper-letter upper-index))

                 (if (and upper-letter lower-index)
                   (make-key upper-letter lower-index)))))

(defn hash-free-adjacent-squares
  [square]
  ;; pegar o estado atual do tabuleiro
  ;; adicionar algo para checar peças inimigas capturáveis
  (filter (fn [x] (= (get initial-table-state x) 0))
          (list-adjacent-squares square)))

(defn list-available-pieces
  [x]
  (if (= x "w")
    "white pieces"
    "black pieces"))

(defn list-legal-moves
  ;; start with regular stuff
  ;; then implement capturing
  [x] ;; gets a square in 'a1' format
  (let [
        free (hash-free-adjacent-squares x)
        ]
    free))

(defn move-piece
  ;; gets a piece-pos and a new-pos in key format
  ;; this should be ran only if the moves are legal
  [piece-pos new-pos]
  (let [
        piece-color (piece-pos initial-table-state)
        ]
  (update (update initial-table-state new-pos piece-color) piece-pos 0)))

(defn someone-won
  [state]
  )
