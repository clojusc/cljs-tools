(defproject clojusc/cljs-tools "0.1.0"
  :description "Useful functions for cljs-based projects"
  :url "https://github.com/clojusc/cljs-tools"
  :license
    {:name "Apache License, Version 2.0"
     :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies
    [[org.clojure/clojure "1.8.0"]
     [org.clojure/clojurescript "1.9.293"]]
  :plugins
    [[lein-cljsbuild "1.1.4"]]
  :clean-targets ^{:protect false}
    ["resources/public/js"
     "target"]
  :cljsbuild {
    :builds [
      {:id "cljs-tools"
       :source-paths ["src"]
       :compiler
         {:main "cljs-tools.core"
          :asset-path "js/out"
          :output-to "resources/public/js/cljs_tools.js"
          :output-dir "resources/public/js"}}
      {:id "node"
       :source-paths ["src"]
       :compiler
         {:target :nodejs
          :output-to "target/node/cljs_tools.js"
          :output-dir "target/node"}}]}
  :aliases {
    "rhino-repl"
      ^{:doc "Start a Rhino-based Clojurescript REPL"}
      ["trampoline" "run" "-m" "clojure.main"
       "dev-resources/scripts/rhino-repl.clj"]
    "node-repl"
      ^{:doc "Start a Node.js-based Clojurescript REPL"}
      ["trampoline" "run" "-m" "clojure.main"
       "dev-resources/scripts/node-repl.clj"]
    "browser-repl"
      ^{:doc "Start a browser-based Clojurescript REPL"}
      ["trampoline" "run" "-m" "clojure.main"
       "dev-resources/scripts/browser-repl.clj"]
     }
  :profiles {
    :test {
      :plugins [
        [jonase/eastwood "0.2.3" :exclusions [org.clojure/clojure]]
        [lein-kibit "0.1.2" :exclusions [org.clojure/clojure]]]
      :test-selectors {
        :default :unit
        :unit :unit
        :system :system
        :integration :integration}
      :source-paths ["test/clj"]}
    :dev {
      :source-paths ["dev-resources/src"]
      :repl-options {:init-ns clojusc.cljs-tools.dev}
      :dependencies [
        [org.clojure/tools.namespace "0.2.11"
         :exclusions [org.clojure/clojure]]]}})
