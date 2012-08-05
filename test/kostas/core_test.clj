
(ns kostas.core-test
  (:use midje.sweet
        kostas.core))

(def data [{:label "a" :features [1 1]}
           {:label "a" :features [1 1]}
           {:label "b" :features [0 0]}])

(fact 
  (classify [0 0] data 2) => "a"
  (classify [1 1] data 2) => "b")

