(ns devcards-playground.core
  (:require
    [devcards.core]
    [reagent.core :as reagent])
  (:require-macros
    [devcards.core :as dc :refer [defcard defcard-rg defcard-doc]]))

; app state reagent atom (keeps track of where it is dereferenced so those places receive updates upon change)
(def click-count (reagent/atom 0))

; simple Reagent counter component
(defn counting-component []
  [:div
   "The atom " [:code "click-count"] " has value: "
   @click-count ". "
   [:button {:on-click #(swap! click-count inc)} "Click meaooouuuu!"]])

(defcard reagent-counting-component
         (dc/reagent counting-component)
         click-count
         {:inspect-data true :history true })

(defn __ [s1 s2]
  (clojure.set/union
    (clojure.set/difference s1 s2)
    (clojure.set/difference s2 s1)
  ))

(js/console.log
  (= (__ #{1 2 3 4 5 6} #{1 3 5 7}) #{2 4 6 7})
  (= (__ #{:a :b :c} #{}) #{:a :b :c})
  (= (__ #{} #{4 5 6}) #{4 5 6})
  (= (__ #{[1 2] [2 3]} #{[2 3] [3 4]}) #{[1 2] [3 4]})
  )

(defcard-doc
  "some documentation on `__`"
  (dc/mkdn-pprint-source __)
  "that was it!")