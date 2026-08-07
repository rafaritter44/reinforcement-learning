(ns tic-tac-toe.core
  (:require [tic-tac-toe.training :as training]))

(comment
  (def model
    (training/train 100000 {:alpha   0.1    ; Learning rate
                            :epsilon 0.1})) ; Exploration rate
  model
  )
