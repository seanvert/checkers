(ns checkers.core-test
  (:require [clojure.test :refer :all]
            [checkers.core :refer :all]))

;; (deftest a-test
;;   (testing "FIXME, I fail."
;;     (is (= 0 1))))

(deftest mov-test
  (testing "Teste dos movimentos possíveis stub"
      (is (= (list-legal-moves) "todo"))))

(deftest state-test
  (testing "Teste do estado do tabuleiro stub"
    (is (= (mainloop) "todo"))))

(deftest adjacent-squares-test
  (testing "Teste de casas adjacentes"
    (is (= (list-adjacent-squares "b3") (hash-set :a2 :b2 :c4 :b4)))
    (is (= (list-adjacent-squares "c4") (hash-set :b3 :c3 :c5 :d5)))
    ))
