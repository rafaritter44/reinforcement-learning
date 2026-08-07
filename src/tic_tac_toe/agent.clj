(ns tic-tac-toe.agent
  (:require [tic-tac-toe.board          :as board]
            [tic-tac-toe.value-function :as value-function]))

(defn- greedy-successors [values boards]
  (let [max-value (apply max (map #(value-function/value-of values %) boards))]
    (filterv #(= max-value
                 (value-function/value-of values %))
             boards)))

(defn choose-move [values board epsilon]
  (let [next-boards    (board/successors board :x)
        greedy-boards  (greedy-successors values next-boards)
        greedy-board   (rand-nth greedy-boards)
        other-boards   (vec (remove (set greedy-boards) next-boards))
        explore?       (and (seq other-boards)
                            (< (rand) epsilon))
        selected-board (if explore?
                         (rand-nth other-boards)
                         greedy-board)]
    {:board        selected-board
     :exploratory? explore?}))
