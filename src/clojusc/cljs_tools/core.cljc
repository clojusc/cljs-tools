(ns clojusc.cljs-tools.core
  "General utility functions."
  (:require [clojure.string :as string]
            [clojure.walk :as walk]
            #?@(:cljs [
            [goog.string :as gstring]
            [goog.string.format]]))
  (:refer-clojure :exclude [format]))

#?(:clj
  (def format #'clojure.core/format))

#?(:cljs
  (do
    (def format #'gstring/format)

    (defn now []
      (js/Date.))

    (defn now-epoch []
      (.getTime (now)))

    (defn now-iso []
      (.toISOString (now)))))

(defn dash->under [str]
  (string/replace str "-" "_"))

(defn under->dash [str]
  (string/replace str "_" "-"))

(defn get-home
  ""
  []
  #?(:clj (System/getenv "HOME")
     :cljs (aget js/process "env" "HOME")))

(defn expand-home
  ""
  [filename]
  (string/replace-first filename "~" (get-home)))

#?(:cljs
  (defn jsx->clj
    [data & {:keys [nested? check?]
             :or {nested? true check? true}}]
    (cond
      (and (not nested?) (not check?))
        (into {} (map #(vector % (aget data %)) (.keys js/Object data)))
      (and (not nested?) check?)
        (if (object? data)
          (into {} (map #(vector % (aget data %)) (.keys js/Object data)))
          data)
      (and nested? (not check?))
        (into {} (map #(vector % (jsx->clj (aget data %)
                                           :nested? nested?
                                           :check? check?))
                      (.keys js/Object data)))
      (and nested? check?)
        (if (object? data)
          (into {} (map #(vector % (jsx->clj (aget data %)
                                               :nested? nested?
                                               :check? check?))
                          (.keys js/Object data)))
          data))))
