(defproject todos "0.1.0-SNAPSHOT"
  :description "A simple clean architecture example in Clojure"
  
  :url "https://github.com/brianium/clean-todos"
  
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  
  :dependencies [[org.clojure/clojure "1.9.0-alpha17"]
                 [org.clojure/core.async "0.3.443"]
                 [org.clojure/java.jdbc "0.7.0"]
                 [clj-time "0.14.0"]
                 [honeysql "0.9.0"]
                 [org.xerial/sqlite-jdbc "3.19.3"]
                 [mount "0.1.11"]]

  :source-paths ["src"]
  
  :profiles {:dev {:dependencies [[org.clojure/test.check "0.10.0-alpha2"]
                                  [com.cemerick/pomegranate "0.3.1"]]
                   :source-paths ["src" "test"]
                   :plugins      [[venantius/ultra "0.5.1"]
                                  [com.jakemccrary/lein-test-refresh "0.20.0"]
                                  [lein-kibit "0.1.6-beta2"]]
                   :test-refresh {:notify-command ["terminal-notifier" "-title" "Tests" "-message"]}}

             :cli {:dependencies [[org.clojure/tools.cli "0.3.5"]
                                  [io.aviso/pretty "0.1.34"]]

                   :plugins [[lein-binplus "0.6.2"]]

                   :main todos.delivery.cli.core}

             
             :api {:dependencies [[ring/ring-core "1.6.2"]
                                  [ring/ring-jetty-adapter "1.6.2"]
                                  [ring/ring-json "0.4.0"]
                                  [ring/ring-defaults "0.3.1"]
                                  [compojure "1.6.0"]]

                   :plugins [[lein-ring "0.12.0"]]

                   :ring {:handler       todos.delivery.api.core/app
                          :init          mount.core/start
                          :port          4242}}

             :uberjar {:aot :all}}
  
  :bin {:name "todos"})
