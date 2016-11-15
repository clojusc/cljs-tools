(ns clojusc.cljs-tools.dev.browser
  (:require [clojure.browser.repl :as repl]
            [clojure.pprint :refer [print-table]]
            [clojure.reflect :refer [reflect]]
            [clojure.string :as string]
            [clojusc.cljs-tools :as tools]))

(repl/connect "http://localhost:9000/repl")

(enable-console-print!)

(println "Hello world!")
