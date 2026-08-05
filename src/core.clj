(ns core)

;; Representing the board

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

(defn terminal? [board]
  (or (winner board)
      (full? board)))

(defn empty-squares [board]
  (keep-indexed (fn [index square]
                  (when (nil? square)
                    index))
                board))

(defn make-move [board square player]
  (assoc board square player))

(defn successors [board player]
  (mapv #(make-move board % player)
        (empty-squares board)))

;; The value function

(defn initial-value [board]
  (cond
    (= :x (winner board)) 1.0
    (terminal? board) 0.0
    :else 0.5))

(defn value-of [values board]
  (get values board (initial-value board)))

;; Choosing a move

(defn greedy-successors [values boards]
  (let [max-value (apply max (map #(value-of values %) boards))]
    (filterv #(= max-value
                 (value-of values %))
             boards)))

(defn rand-elem [xs]
  (nth xs (rand-int (count xs))))

(defn choose-move [values board epsilon]
  (let [next-boards    (successors board :x)
        greedy-boards  (greedy-successors values next-boards)
        greedy-board   (rand-elem greedy-boards)
        other-boards   (vec (remove (set greedy-boards)
                                    next-boards))
        explore?       (and (seq other-boards)
                            (< (rand) epsilon))
        selected-board (if explore?
                         (rand-elem other-boards)
                         greedy-board)]
    {:board        selected-board
     :exploratory? explore?}))

;; Temporal Difference (TD) update

(defn td-update [values state next-state alpha]
  (let [old-value  (value-of values state)
        next-value (value-of values next-state)
        new-value  (+ old-value
                      (* alpha
                         (- next-value old-value)))]
    (assoc values state new-value)))
