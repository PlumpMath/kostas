
(ns kostas.core)

(defn- with-distance 
  [from {:keys [label features]}]
  (let [distance (comp #(* % %) -)
        squares (map distance from features)]
    {:label label
     :distance (Math/sqrt (reduce + 0 squares))}))

(defn classify
  [x data k]
  (->> data
    (map (partial with-distance x))
    (sort-by :distance)
    (take k)
    (group-by :label)
    (apply max-key count)))

