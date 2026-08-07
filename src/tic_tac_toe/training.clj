(ns tic-tac-toe.training
  (:require [tic-tac-toe.game :as game]))

(defn train [games options]
  (loop [remaining games
         values    {}
         results   {:x-wins 0
                    :o-wins 0
                    :draw   0}]
    (if (zero? remaining)
      {:values  values
       :results results}
      (let [{new-values :values
             outcome    :outcome}
            (game/play-game values options)]
        (recur (dec remaining)
               new-values
               (update results outcome inc))))))
