(ns checkers.core
  (:require [clojure.set :only (difference)])
  (:gen-class))

;; estado inicial do tabuleiro
(load "state")
(load "moves")

(defn -main
  []
  (loop
      [state initial-table-state
       turn 0]
    (print state)
    (print "\n")
    (print (color-turn turn))
    (let
        [current-move-code (color-turn turn)
         move (get-move-input current-move-code state)
         piece-to-be-moved (get-key-from-string (first move))
         available-moves (second move)
         new-pos (select-move-from-list available-moves)
         new-state (move-piece state piece-to-be-moved new-pos)
         finished? (someone-won state)]
      (if finished?
        (print "end todo")
        (recur new-state (inc turn))))))

;; DONE check turns
;; DONE validate entries and check for legal moves
;; TODO check if someone won
;; get another player position if no one won
;; calculate score
