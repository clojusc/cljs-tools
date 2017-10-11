(defproject clojusc/cljs-tools "0.2.0"
  :description "Useful functions for cljs-based projects"
  :url "https://github.com/clojusc/cljs-tools"
  :license
    {:name "Apache License, Version 2.0"
     :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :exclusions [org.clojure/clojure]
  :dependencies [
    [joda-time/joda-time "2.9.9"]
    [org.clojure/clojure "1.8.0"]
    [org.clojure/clojurescript "1.9.946"]]
  :plugins [
    [lein-cljsbuild "1.1.7"]]
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
          :output-dir "target/node"
          :main clojusc.cljs-tools}}]}
  :profiles {
    :ubercompile {
      :aot :all}
    :test {
      :test-selectors {
        :default :unit
        :unit :unit
        :system :system
        :integration :integration}
      :source-paths ["test/clj"]
      :dependencies [
        [org.clojure/tools.namespace "0.2.11"]]
      :plugins [
        [jonase/eastwood "0.2.4"]
        [lein-ancient "0.6.12"]
        [lein-bikeshed "0.4.1"]
        [lein-kibit "0.1.5"]
        [lein-shell "0.5.0"]
        [venantius/yagni "0.1.4"]]}
    :dev {
      :source-paths ["dev-resources/src"]
      :repl-options {:init-ns clojusc.cljs-tools.dev}
      :dependencies [
        [org.clojure/tools.namespace "0.2.11"
         :exclusions [org.clojure/clojure]]]}}
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
    "check-deps" [
      "with-profile" "+test" "ancient" "check" ":all"]
    "kibit" [
      "with-profile" "+test" "do"
        ["shell" "echo" "== Kibit =="]
        ["kibit"]]
    "outlaw" [
      "with-profile" "+test"
      "eastwood" "{:namespaces [:source-paths] :source-paths [\"src\"]}"]
    "lint" [
      "with-profile" "+test" "do"
        ["check"] ["kibit"] ["outlaw"]]
    "build" ["with-profile" "+test" "do"
      ["check-deps"]
      ;["lint"]
      ["test"]
      ["compile"]
      ["with-profile" "+ubercompile" "compile"]
      ["clean"]
      ["uberjar"]]})
