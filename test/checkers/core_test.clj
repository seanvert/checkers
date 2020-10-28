(ns checkers.core-test
  (:require [clojure.test :refer :all]
            [checkers.core :refer :all]))

(deftest get-key-from-string-test
  (testing "Teste da função que monta keys"
    (is (= (get-key-from-string "a1") :a1))
    (is (= (get-key-from-string "a7") :a7))
    (is (= (get-key-from-string "h8") :h8))
    (is (= (get-key-from-string "f2") :f2))
    (is (= (get-key-from-string "g3") :g3))
    (is (= (get-key-from-string "c7") :c7))
    (is (= (get-key-from-string "f6") :f6))))

(deftest make-key-test
  (is (= (make-key "a1") :a1))
  )

;; FIXME esse with-in-str parece que não funciona com let expressions
;; depois preciso ver algum outro jeito para testar isso
;; (deftest state-test
;;   (testing "Teste do estado do tabuleiro stub"
;;     (is (= (with-in-str "a1" (-main initial-table-state 0))) "todo"))))

(deftest list-adjacent-squares-test
  (testing "Teste de casas adjacentes"
    (is (= (list-adjacent-squares "c3") (list :b2 :b4 :d4 :d2)))
    (is (= (list-adjacent-squares "h2") (list :g1 :g3)))
    (is (= (list-adjacent-squares "a1") '(:b2)))
    (is (= (list-adjacent-squares "h2") '(:g1 :g3)))
    (is (= (list-adjacent-squares "a7") '(:b8 :b6)))
    (is (= (list-adjacent-squares "h8") '(:g7)))))

(deftest free-adjacent-squares-test
  (testing "Teste da função que retorna as casas adjacentes livres"
    (is (= (free-adjacent-squares initial-table-state "h2") '()))
    (is (= (free-adjacent-squares initial-table-state "g3") '(:f4 :h4)))
    (is (= (free-adjacent-squares initial-table-state "f6") '(:e5 :g5)))
    (is (= (free-adjacent-squares initial-table-state "c3") '(:b4 :d4)))))

(deftest list-legal-moves-test
  (testing "Teste da função que lista os movimentos legais"
    (is (= (list-legal-moves initial-table-state "a1") '()))
    (is (= (list-legal-moves initial-table-state "c1") '()))
    (is (= (list-legal-moves initial-table-state "e1") '()))
    (is (= (list-legal-moves initial-table-state "g1") '()))
    (is (= (list-legal-moves initial-table-state "b2") '()))
    (is (= (list-legal-moves initial-table-state "d2") '()))
    (is (= (list-legal-moves initial-table-state "f2") '()))
    (is (= (list-legal-moves initial-table-state "h2") '()))
    (is (= (list-legal-moves initial-table-state "b8") '()))
    (is (= (list-legal-moves initial-table-state "d8") '()))
    (is (= (list-legal-moves initial-table-state "f8") '()))
    (is (= (list-legal-moves initial-table-state "h8") '()))
    (is (= (list-legal-moves initial-table-state "a7") '()))
    (is (= (list-legal-moves initial-table-state "c7") '()))
    (is (= (list-legal-moves initial-table-state "e7") '()))
    (is (= (list-legal-moves initial-table-state "g7") '()))))

(deftest move-piece-test
  (testing "Teste do movimento das peças um caso de teste"
    (is (= (move-piece initial-table-state :a3 :b4)
           {:a1 1
            :b2 1
            :c1 1
            :d2 1
            :e1 1
            :f2 1
            :g1 1
            :h2 1
            :a3 0
            :c3 1
            :e3 1
            :g3 1
            :a5 0
            :b4 1
            :c5 0
            :d4 0
            :e5 0
            :f4 0
            :g5 0
            :h4 0
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
            :h6 2}))))

(deftest someone-won-test
  (testing "Teste para ver se alguém venceu o jogo"
    (is (= (someone-won initial-table-state) false))
    (is (= (someone-won            {:a1 0
                                    :b2 0
                                    :c1 0
                                    :d2 0
                                    :e1 0
                                    :f2 0
                                    :g1 0
                                    :h2 0
                                    :a3 0
                                    :c3 0
                                    :e3 0
                                    :g3 0
                                    :a5 0
                                    :b4 0
                                    :c5 0
                                    :d4 0
                                    :e5 0
                                    :f4 0
                                    :g5 0
                                    :h4 0
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
                                    :h6 2}) "black"))
    (is (= (someone-won            {:a1 1
                                    :b2 1
                                    :c1 1
                                    :d2 1
                                    :e1 1
                                    :f2 1
                                    :g1 1
                                    :h2 1
                                    :a3 0
                                    :c3 1
                                    :e3 1
                                    :g3 1
                                    :a5 0
                                    :b4 1
                                    :c5 0
                                    :d4 0
                                    :e5 0
                                    :f4 0
                                    :g5 0
                                    :h4 0
                                    :a7 0
                                    :b8 0
                                    :b6 0
                                    :c7 0
                                    :d8 0
                                    :d6 0
                                    :e7 0
                                    :f8 0
                                    :f6 0
                                    :g7 0
                                    :h8 0
                                    :h6 0}) "white"))))


(deftest color-turn-test
  (is (= (color-turn 0) 2))
  (is (= (color-turn 1) 1))
  (is (= (color-turn 2) 2))
  (is (= (color-turn 3) 1))
  (is (= (color-turn 4) 2))
  (is (= (color-turn 5) 1))
  (is (= (color-turn 6) 2))
  (is (= (color-turn 7) 1))
  (is (= (color-turn 8) 2))
  (is (= (color-turn 9) 1))
  (is (= (color-turn 10) 2))
  )

(deftest get-move-input-test
  (is (= (get-move-input 2 initial-table-state)) "todo")
  )

(deftest select-move-from-list-test
  (is (= (select-move-from-list (list ())) '()))
  )
