(defproject clexec "1.0.0"
  :description "CLojure EXECute: Library for executing commands from shell"
  :url "https://github.com/dxlr8r/clexec"
  :license {:name "Modified BSD license"
            :url "https://github.com/dxlr8r/clexec/blob/master/LICENSE"}
  :main clexec.core
  :aot [clexec.core]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [clj-pid "0.1.2"]])
