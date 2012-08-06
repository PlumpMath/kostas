
(ns kostas.core
  (:use incanter.core))

(defn- with-distance 
  [from {:keys [label features]}]
  (let [squares (map (comp #(* % %) -)
                     from features)]
    {:label label
     :distance (Math/sqrt (reduce + 0 squares))}))

(defn factors-for
  [col]
  (let [minVal (apply min col)
        maxVal (apply max col)]
    (/ 1 (- maxVal minVal))))

(defn- normalise-data
  [factors data]
  (assoc-in data [:features]
    (map * factors (:features data))))

(defn- normalise
  [data]
  (let [factors (->> data
                  (map :features)
                  (trans)
                  (map factors-for))]
    (map (partial normalise-data factors) data)))

(defn classify
  [x data k]
  (->> (normalise data)
    (map (partial with-distance x))
    (sort-by :distance)
    (take k)
    (group-by :label)
    (apply max-key #(count (val %)))
    (first)))

