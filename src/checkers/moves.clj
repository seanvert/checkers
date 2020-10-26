(defn get-key-from-string
  [entry]
  (read-string (str ":" entry)))

(defn make-key
  ;; pega a x LETRA e o y NÙMERO e retorna uma key de hash
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
      [letter (first square)
       index (subs square 1 2)
       int-letter (int letter)
       int-index (Integer/parseInt index)]
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
  (let
      [free (free-adjacent-squares state x)]
    free))

(defn move-piece
  ;; gets a piece-pos and a new-pos in key format
  ;; this should run only if the moves are legal
  [state piece-pos new-pos]
  (let [piece-color (piece-pos state)
        inc-prop (if (= piece-color 1)
                   inc
                   #(+ % 2))
        dec-prop (if (= piece-color 1)
                   dec
                   #(- % 2))]
  (update (update state new-pos inc-prop) piece-pos dec-prop)))

(defn someone-won
  [state]
  "todo")


(defn color-turn [turn]
  (if (= (mod turn 2) 0)
    2
    1))


(defn get-input-and-validate []
  (loop [input (read-line)]
    (if (not= (count input) 2)
      (recur (read-line))
      (if (Character/isLetter (first input))
        (if (Character/isDigit (second input))
          input
          (recur (read-line)))
        (recur (read-line))))))

(defn get-move-input [current-move-code state]
  (loop [square (get-input-and-validate)]
    ;; checa se a peça é do jogador atual
    (if (= current-move-code
           ;; cor da peça na casa do estado do tabuleiro
           ((get-key-from-string square) state))
      (let
          [legal-moves (list-legal-moves state square)]
        (if (empty? legal-moves)
          (recur (read-line))
          (list square legal-moves)))
      (recur (read-line)))))

(defn select-move-from-list [list]
  (do (print list)
      (print "\n"))
  (loop [move (Integer/parseInt (read-line))]
    (if (< move (count list))
      (nth list move)
      (recur (Integer/parseInt (read-line))))))
