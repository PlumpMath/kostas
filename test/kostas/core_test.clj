
(ns kostas.core-test
  (:use midje.sweet
        kostas.core))

(def data [{:label "a" :features [1 1]}
           {:label "a" :features [1 1]}
           {:label "b" :features [0.1 0.1]}
           {:label "b" :features [0 0]}])

(fact 
  (classify [1 1] data 3) => "a"
  (classify [0 0] data 3) => "b")

