(defproject clojusc/cljs-tools "0.2.0"
  :description "Useful functions for cljs-based projects"
  :url "https://github.com/clojusc/cljs-tools"
  :license
    {:name "Apache License, Version 2.0"
     :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies
    [[joda-time/joda-time "2.10"]
     [org.clojure/clojure "1.9.0"]
     [org.clojure/clojurescript "1.10.339"]]
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
    :lint {
      :source-paths ^:replace ["src"]
      :test-paths ^:replace []
      :plugins [
        [jonase/eastwood "0.3.1"]
        [lein-ancient "0.6.15"]
        [lein-bikeshed "0.5.1"]
        [lein-kibit "0.1.6"]
        [lein-shell "0.5.0"]
        [venantius/yagni "0.1.6"]]}
    :dev {
      :source-paths ["dev-resources/src"]
      :repl-options {
        :init-ns clojusc.cljs-tools.dev}
      :dependencies [
        [org.clojure/tools.namespace "0.2.11"]]}
    :test {
      :aot :all
      :dependencies [
        [org.clojure/tools.namespace "0.2.11"]]
      :plugins [
        [lein-ltest "0.3.0"]]
      :source-paths ["test/clj"]
      :test-selectors {
        :default :unit
        :unit :unit
        :system :system
        :integration :integration}}}
  :aliases {
    ;; CLJS aliases
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
    ;; CLJ Aliases
    "repl" ["do"
      ["clean"]
      ["repl"]]
    "ubercompile" ["with-profile" "+ubercompile" "compile"]
    ;; Linting and tests
    "check-vers" ["with-profile" "+lint" "ancient" "check" ":all"]
    "check-jars" ["with-profile" "+lint" "do"
      ["deps" ":tree"]
      ["deps" ":plugin-tree"]]
    "check-deps" ["do"
      ["check-jars"]
      ["check-vers"]]
    "kibit" ["with-profile" "+lint" "do"
      ["shell" "echo" "== Kibit =="]
      ["kibit"]]
    "eastwood" ["with-profile" "+lint" "eastwood" "{:namespaces [:source-paths]}"]
    "lint" ["do"
      ["kibit"]
      ;["eastwood"]
      ]
    "ltest" ["with-profile" "+test" "ltest"]
    ;; Build
    "build" ^{:doc "Perform build steps."} ["do"
      ["clean"]
      ["ubercompile"]
      ["check-vers"]
      ;["lint"]
      ["ltest"]
      ["uberjar"]]})
