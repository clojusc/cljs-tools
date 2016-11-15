all: clj jar cljs

clj:
	lein compile

jar:
	lein jar

cljs:
	lein cljsbuild once cljs-tools

node:
	lein cljsbuild once node

clean:
	lein clean
	rm -rf .repl-* pom.xml*

travis: clean clj jar node cljs check
