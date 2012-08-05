
kostas
======

Kostas is a simple K Nearest Neighbour classifier for an arbitrary
count of numerical features.

Simple Usage
------------

Pass your data to classify in the following format.

```clojure
(def data [{:label "Foo" :features [1 2 3]}
           {:label "Bar" :features [3 4 2]}])
(def to-classify [1 2 2])

(classify to-classify data 3)
```

The label for the matched classification will be returned.

