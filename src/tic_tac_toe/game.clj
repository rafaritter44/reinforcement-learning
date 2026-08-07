(ns tic-tac-toe.game
  (:require [tic-tac-toe.board          :as board]
            [tic-tac-toe.value-function :as value-function]
            [tic-tac-toe.agent          :as agent]
            [tic-tac-toe.opponent       :as opponent]))

(defn play-game [values {:keys [alpha epsilon]}]
  (loop [board  board/empty-board
         values values]
    ;; X's turn
    (let [{x-board :board, exploratory? :exploratory?} (agent/choose-move values board epsilon)

          ;; Learn only if this was a greedy move.
          values-after-x (if exploratory? values (value-function/td-update values board x-board alpha))]
      (cond
        ;; X won.
        (= :x (board/winner x-board))
        {:values values-after-x, :outcome :x-wins}

        ;; Board filled after X's move.
        (board/full? x-board)
        {:values values-after-x, :outcome :draw}

        :else
        ;; O's turn
        (let [o-board        (opponent/random-opponent x-board)
              values-after-o (value-function/td-update values-after-x x-board o-board alpha)]
          (if (= :o (board/winner o-board))
            {:values values-after-o, :outcome :o-wins} ; O won.
            (recur o-board values-after-o)))))))
