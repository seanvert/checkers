(ns checkers.core-test
  (:require [clojure.test :refer :all]
            [checkers.core :refer :all]))

(deftest mov-test
  (testing "Teste dos movimentos possíveis stub"
    (is (= (list-legal-moves "a3") '(:b4)))))

(deftest state-test
  (testing "Teste do estado do tabuleiro stub"
    (is (= (-main) "todo"))))

(deftest adjacent-squares-test
  (testing "Teste de casas adjacentes"
    (is (= (list-adjacent-squares "c3") (hash-set :d2 :b2 :d4 :b4)))
    (is (= (list-adjacent-squares "h2") (hash-set :g3 :g1)))
    ))

(deftest move-piece-test
  (testing "Teste do movimento das peças um caso de teste"
    (is (= (move-piece :a3 :b4)
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
            :a3 0
            :c3 1
            :e3 1
            :g3 1
            ;; empty squares
            :a5 0
            :b4 1
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
            }))))

(deftest list-legal-moves-test
  (testing "Teste da função que lista os movimentos legais"
    (is (= (list-legal-moves "a1") '()))
    (is (= (list-legal-moves "c1") '()))
    (is (= (list-legal-moves "e1") '()))
    (is (= (list-legal-moves "g1") '()))
    (is (= (list-legal-moves "b2") '()))
    (is (= (list-legal-moves "d2") '()))
    (is (= (list-legal-moves "f2") '()))
    (is (= (list-legal-moves "h2") '()))
    (is (= (list-legal-moves "b8") '()))
    (is (= (list-legal-moves "d8") '()))
    (is (= (list-legal-moves "f8") '()))
    (is (= (list-legal-moves "h8") '()))
    (is (= (list-legal-moves "a7") '()))
    (is (= (list-legal-moves "c7") '()))
    (is (= (list-legal-moves "e7") '()))
    (is (= (list-legal-moves "g7") '()))))

(deftest hash-free-adjacent-squares-test
  (testing "Teste da função que retorna o hash das casas adjacentes livres"
    (is (= (hash-free-adjacent-squares "h2") '()))
    (is (= (hash-free-adjacent-squares "g3") '(:h4 :f4)))
    (is (= (hash-free-adjacent-squares "f6") '(:e5 :g5)))
    (is (= (hash-free-adjacent-squares "c3") '(:d4 :b4)))))

(deftest list-adjacent-squares-test
  (testing "Teste da função que retorna o hash das casas adjacentes"
    (is (= (list-adjacent-squares "a1") #{:b2}))
    (is (= (list-adjacent-squares "h2") #{:g3 :g1}))
    (is (= (list-adjacent-squares "a7") #{:b8 :b6}))
    (is (= (list-adjacent-squares "h8") #{:g7}))))

(deftest get-key-from-string-test
  (testing "Teste da função que monta keys"
    (is (= (get-key-from-string "a1") :a1))
    (is (= (get-key-from-string "a7") :a7))
    (is (= (get-key-from-string "h8") :h8))
    (is (= (get-key-from-string "f2") :f2))
    (is (= (get-key-from-string "g3") :g3))
    (is (= (get-key-from-string "c7") :c7))
    (is (= (get-key-from-string "f6") :f6))
    ))
