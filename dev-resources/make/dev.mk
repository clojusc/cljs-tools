repl:
	@rlwrap \
		--command-name=clojure \
		--prompt-colour=yellow \
		lein repl

node-repl: node
	@rlwrap \
		--command-name=cljs-node \
		--prompt-colour=yellow \
		--substitute-prompt="clojusc.cljs-tools.dev.nodejs=> " \
		--only-cook=".*=>" \
		lein node-repl

rhino-repl: cljs
	@rlwrap \
		--command-name=cljs-rhino \
		--prompt-colour=yellow \
		--substitute-prompt="clojusc.cljs-tools.dev.rhino=> " \
		--only-cook=".*=>" \
		lein rhino-repl
