# cljs-tools

[![Build Status][travis-badge]][travis]
[![Dependencies Status][deps-badge]][deps]
[![Clojars Project][clojars-badge]][clojars]
[![Tag][tag-badge]][tag]
[![JDK version][jdk-v]](.travis.yml)
[![Clojure version][clojure-v]](project.clj)

[![][logo]][logo-large]

*Useful functions for cljs-based projects*


#### Contents

* [Usage](#usage-)
* [License](#license-)


## Usage [&#x219F;](#contents)


```
$ make rhino-repl
```
```clj
clojusc.cljs-tools.dev.rhino=> (require '[clojusc.cljs-tools.core :as tools])
```

Then call a supported function:

```clj
clojusc.cljs-tools.dev.rhino=> (tools/now-iso)
"2016-11-15T00:34:22.612Z"
```

Note that some functions will only work in Node.js-based REPLs (e.g., anything
accessing the enviroinment).


## License [&#x219F;](#contents)

Copyright © 2016-2018, Clojure-Aided Enrichment Center

Copyright © 2016, Element 84, Inc.

Apache License, Version 2.0.


<!-- Named page links below: /-->

[travis]: https://travis-ci.org/clojusc/cljs-tools
[travis-badge]: https://travis-ci.org/clojusc/cljs-tools.png?branch=master
[deps]: http://jarkeeper.com/clojusc/cljs-tools
[deps-badge]: http://jarkeeper.com/clojusc/cljs-tools/status.svg
[logo]: resources/images/logo.png
[logo-large]: resources/images/logo-large.png
[tag-badge]: https://img.shields.io/github/tag/clojusc/cljs-tools.svg
[tag]: https://github.com/clojusc/cljs-tools/tags
[clojure-v]: https://img.shields.io/badge/clojure-1.8.0-blue.svg
[jdk-v]: https://img.shields.io/badge/jdk-1.7+-blue.svg
[clojars]: https://clojars.org/clojusc/cljs-tools
[clojars-badge]: https://img.shields.io/clojars/v/clojusc/cljs-tools.svg
