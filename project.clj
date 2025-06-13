(defproject sk "0.1.0"
  :description "TCC Platform" ; Change me
  :url "http://example.com/FIXME" ; Change me - optional
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.12.0"]
                 [org.clojure/data.csv "1.1.0"]
                 [org.slf4j/slf4j-simple "2.0.17"]
                 [compojure "1.7.1"]
                 [hiccup "1.0.5"]
                 [buddy/buddy-hashers "2.0.167"]
                 [com.draines/postal "2.0.5"]
                 [cheshire "6.0.0"]
                 [clj-pdf "2.7.0"]
                 [ondrs/barcode "0.1.0"]
                 [pdfkit-clj "0.1.7"]
                 [cljfmt "0.9.2"]
                 [clj-jwt "0.1.1"]
                 [clj-time "0.15.2"]
                 [date-clj "1.0.1"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [mysql/mysql-connector-java "8.0.33"]
                 [ragtime "0.8.1"]
                 [ring/ring-core "1.14.1"]
                 [ring/ring-jetty-adapter "1.14.1"]
                 [ring/ring-defaults "0.6.0"]
                 [ring/ring-devel "1.14.1"]
                 [ring/ring-codec "1.3.0"]]
  :main ^:skip-aot tcc.core
  :aot [tcc.core]
  :plugins [[lein-ancient "0.7.0"]
            [lein-pprint "1.3.2"]]
  :uberjar-name "tcc.jar"
  :target-path "target/%s"
  :ring {:handler tcc.core
         :auto-reload? true
         :auto-refresh? false}
  :resources-paths ["shared" "resources"]
  :aliases {"migrate" ["run" "-m" "tcc.migrations/migrate"]
            "rollback" ["run" "-m" "tcc.migrations/rollback"]
            "database" ["run" "-m" "tcc.models.cdb/database"]
            "grid" ["run" "-m" "tcc.builder/build-grid"]
            "dashboard" ["run" "-m" "tcc.builder/build-dashboard"]
            "report" ["run" "-m" "tcc.builder/build-report"]}
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}
             :dev {:source-paths ["src" "dev"]
                   :main tcc.dev}})
