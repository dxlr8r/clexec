(ns clexec.sh
  (:import [java.io BufferedReader InputStreamReader]))

(defn- run [process] (.. Runtime getRuntime (exec (str process))))

(defn- cmdout [stdout]
  (let [br (BufferedReader.
           (InputStreamReader.
            (.getInputStream stdout)))]
    (line-seq br)))

(defn sh [command]
  (let [output (cmdout (run command))] (hash-map :cmd command :output output) ))
