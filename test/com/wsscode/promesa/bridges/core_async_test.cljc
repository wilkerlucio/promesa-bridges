(ns com.wsscode.promesa.bridges.core-async-test
  (:require
    [clojure.core.async :as async]
    [clojure.test :refer [deftest is are run-tests testing #?(:cljs async)]]
    [com.wsscode.promesa.bridges.core-async]
    [promesa.core :as p]))

#?(:clj
   (deftest read-from-async-chan-test
     (is (= @(p/let [val (let [c (async/chan)]
                           (async/put! c "value")
                           c)]
               val)
            "value"))))

#?(:cljs
   (deftest read-from-async-chan-test
     (async done
            (async/go
              (is (= (async/<! (p/let [val (let [c (async/chan)]
                                             (async/put! c "value")
                                             c)]
                                 val))
                     "value"))
              (done)))))


