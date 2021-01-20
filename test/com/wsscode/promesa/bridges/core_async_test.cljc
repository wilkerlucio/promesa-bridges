(ns com.wsscode.promesa.bridges.core-async-test
  (:require
    [clojure.core.async :as async]
    [clojure.test :refer [deftest is are run-tests testing]]
    [com.wsscode.promesa.async-bridges.core-async]
    [promesa.core :as p]))

(deftest read-from-async-chan-test
  (is (= @(p/let [val (let [c (async/chan)]
                        (async/put! c "value")
                        c)]
            val)
         "value")))
