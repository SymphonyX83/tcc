(ns tcc.dev
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [tcc.models.crud :refer [config]]
            [tcc.core :as core]))

(defn -main []
  (jetty/run-jetty (wrap-reload #'core/app) {:port (:port config)}))
