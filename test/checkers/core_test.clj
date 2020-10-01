(ns checkers.core-test
  (:require [clojure.test :refer :all]
            [checkers.core :refer :all]))

(deftest mov-test
  (testing "Teste dos movimentos possíveis stub"
    (is (= (list-legal-moves "a3") '(:b4)))))

(deftest state-test
  (testing "Teste do estado do tabuleiro stub"
    (is (= (mainloop) "todo"))))

(deftest adjacent-squares-test
  (testing "Teste de casas adjacentes"
    (is (= (list-adjacent-squares "c3") (hash-set :d2 :b2 :d4 :b4)))
    (is (= (list-adjacent-squares "h2") (hash-set :i3 :g3 :i1 :g1)))
    ))

(deftest move-piece-test
  (testing "Teste do movimento das peças failing"
    (is (= (move-piece :a1 :a2) 0))
    ))

(deftest list-legal-moves-test
  (testing "Teste da função que lista os movimentos legais failing"
    (is (= (list-legal-moves "a1") 0))
    ))

(deftest hash-free-adjacent-squares-test
  (testing "Teste da função que retorna o hash das casas adjacentes livres"
    (is (= (hash-free-adjacent-squares "h2") 0))
    ))

(deftest list-adjacent-squares-test
  (testing "Teste da função que retorna o hash das casas adjacentes"
    (is (= (list-adjacent-squares "a1") 0))
    ))

(deftest get-key-from-string-test
  (testing "Teste da função que monta keys"
    (is (= (get-key-from-string "a1") :a1))
    ))
