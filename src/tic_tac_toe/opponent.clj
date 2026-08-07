(ns tic-tac-toe.opponent
  (:require [tic-tac-toe.board :as board]))

(defn random-opponent [board]
  (rand-nth (board/successors board :o)))
