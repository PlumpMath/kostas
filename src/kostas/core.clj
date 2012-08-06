
(ns kostas.core
  (:use incanter.core))

(defn- with-distance 
  "Returns a map containing the Euclidean distance of the
  features from the specified map"
  [from {:keys [label features]}]
  (let [squares (map (comp #(* % %) -)
                     from features)]
    {:label label
     :distance (Math/sqrt (reduce + 0 squares))}))

(defn- factors-for
  "Return the 1-0 scaling factor for a numeric collection"
  [col]
  (let [minVal (apply min col)
        maxVal (apply max col)]
    (/ 1 (- maxVal minVal))))

(defn- normalise-data
  "Normalises the feature data using the scaling factors"
  [factors data]
  (assoc-in data [:features]
    (map * factors (:features data))))

(defn- normalise
  "Normalises the feature data"
  [data]
  (let [factors (->> data
                  (map :features)
                  (trans)
                  (map factors-for))]
    (map (partial normalise-data factors) data)))

;; Public

(defn classify
  "Classifies x using k-nearest neighbours in data"
  [x data k]
  (->> (normalise data)
    (map (partial with-distance x))
    (sort-by :distance)
    (take k)
    (group-by :label)
    (apply max-key #(count (val %)))
    (first)))

