(ns checkers.core-test
  (:require [clojure.test :refer :all]
            [checkers.core :refer :all]))

;; (deftest a-test
;;   (testing "FIXME, I fail."
;;     (is (= 0 1))))

(deftest mov-test
  (testing "Teste dos movimentos possíveis"
      (is (= (list-legal-moves) "todo")))
  )

(deftest state-test
  (testing "Teste do estado do tabuleiro"
    (is (= (mainloop) "todo"))))
