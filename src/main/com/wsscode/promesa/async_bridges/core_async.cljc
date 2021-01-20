(ns com.wsscode.promesa.async-bridges.core-async
  (:require [clojure.core.async :refer [go <!]]
            #?(:cljs [clojure.core.async.impl.channels :refer [ManyToManyChannel]])
            [promesa.core :as p]
            [promesa.protocols])
  #?(:clj (:import [clojure.core.async.impl.channels ManyToManyChannel])))

; region core.async extension

(defn error?
  "Returns true if err is an error object."
  [err]
  #?(:clj
     (instance? Throwable err)

     :cljs
     (instance? js/Error err)))

(defn chan->promise [c]
  (p/create
    (fn [resolve reject]
      (go
        (let [v (<! c)]
          (if (error? v)
            (reject v)
            (resolve v)))))))

(extend-type ManyToManyChannel
  promesa.protocols/IPromiseFactory
  (-promise [this]
    (chan->promise this)))

; endregion
