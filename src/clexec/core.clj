(ns clexec.core
  (:require
   [clj-pid.core :as pid]
   [clojure.string :refer [join split]]
   [clexec.sh :refer [sh]])
  (:import [sun.misc Signal SignalHandler])
  (:gen-class))

(defn run-commands
  ([cmds] (run-commands cmds true))
  ([cmds background?]
   (doseq [cmd cmds]
     (if background?
       (future (sh cmd))
       (do (println cmd) (sh cmd))))))

(defn list-pids []
  (:output (sh (join " " (list "pgrep" "-P" (pid/current))))))

(defn kill [pid-list]
  (:output (sh (join " " (list "kill" (join " " pid-list))))))

(defn- exit [] (System/exit 0))

(defn- init-signal []
  (Signal/handle (Signal. "USR2")
                 (reify SignalHandler
                   (handle [_ _]
                     (do
                       (kill (list-pids))
                       (exit))))))

(defn -main [_ cmd-str] ; the 1st argument "_" is automatically set from java and is equal to the scripts path
  (do
    (run-commands (split cmd-str #";"))
    (init-signal)
    (println (pid/current))))

;(-main '_' "sleep 500;sleep 500")
;(kill (list-pids))
