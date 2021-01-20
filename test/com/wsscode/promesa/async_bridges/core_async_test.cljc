(ns com.wsscode.promesa.async-bridges.core-async-test
  (:require [clojure.test :refer [deftest is are run-tests testing]]
            [com.wsscode.promesa.async-bridges.core-async]
            [promesa.core :as p]
            [clojure.core.async :as async]))

(deftest read-from-async-chan-test
  (is (= @(p/let [val (let [c (async/chan)]
                        (async/put! c "value")
                        c)]
            val)
         "value")))
