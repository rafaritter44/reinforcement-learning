(ns tic-tac-toe.value-function
  (:require [tic-tac-toe.board :as board]))

(defn- initial-value [board]
  (let [w (board/winner board)]
    (cond
      (= w :x)      1.0
      w             0.0
      (board/full? board) 0.0
      :else         0.5)))

(defn value-of [values board]
  (or (values board)
      (initial-value board)))

(defn td-update [values state next-state alpha]
  (let [old-value  (value-of values state)
        next-value (value-of values next-state)
        new-value  (+ old-value
                      (* alpha
                         (- next-value old-value)))]
    (assoc values state new-value)))
