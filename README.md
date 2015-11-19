# clexec

CLojure EXECute: Library for executing commands from shell

## Why

This code used to be a part of a larger project that was split into three smaller projects (cliprange, cliptools and clexec). When split out, this part alone doesn't do much. As a library you might find some uses of it, or rip out some code. You could use the jar functionality, but I would rather use a regular shell. Be aware that this library makes use of the shell tools: sh, pgrep, kill. Should work on both BSD and GNU versions though.

## Installation

* For installation with [localrepo](https://github.com/kumarshantanu/lein-localrepo):
  * First download the source code, then cd into the source and execute:
    * `lein clean ; lein uberjar ; lein localrepo install target/*-standalone.jar no.ifixit/clexec "1.0.0"`
  * Then you can require the library into clojure as you would any other library:
    * `(require '[clexec.core :refer [run-commands list-pids kill]])`

## Usage

* Jar:
  * `java -jar target/clexec-*.jar 'sleep 60; sleep 60'`, execute commands seperated by ";", then prints out the pid of the jar. Each commands is executed in the background
  * `# kill -USR2 jar-pid`, execute from a shell. Kills all spawned background processes, then the parent process itself
* Functions, examples:
  * `(run-commands (list "sleep 60" "sleep 60"))`, run commands from the list in background
  * `(list-pids)`, lists all, if any, pids of running background processes
  * `(kill (list-pids))`, kills all running background processes, then the parent process itself

## License

Copyright © 2015 Simen Strange Øya

Distributed under the Modified BSD license
