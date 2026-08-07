(ns tic-tac-toe.board)

(def empty-board
  (vec (repeat 9 nil)))

(def winning-lines
  [[0 1 2]
   [3 4 5]
   [6 7 8]
   [0 3 6]
   [1 4 7]
   [2 5 8]
   [0 4 8]
   [2 4 6]])

(defn winner [board]
  (some (fn [[a b c]]
          (let [mark (board a)]
            (when (and mark
                       (= mark (board b))
                       (= mark (board c)))
              mark)))
        winning-lines))

(defn full? [board]
  (every? some? board))

(defn empty-squares [board]
  (keep-indexed
   (fn [index square]
     (when (nil? square)
       index))
   board))

(defn make-move [board square player]
  (assoc board square player))

(defn successors [board player]
  (mapv #(make-move board % player)
        (empty-squares board)))
