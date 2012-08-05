
(ns ^{:doc "Kostas allows doing simple k nearest neighbour
  analysis on supplied data sets.  The data sets should be
  in the following format:
           
  [{:label \"Foo\" :features [1 1 0]}
   {:label \"Bar\" :features [1 0 0]
   ... ]
           
  Then pass this to classify. This only works with numerical
  features, nominal ones will cause an error."}
  kostas.core)

(defn with-distance 
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
    (first)
    (:label)))

