(ns tcc.handlers.oncall.controller
  (:require
   [tcc.handlers.oncall.model :refer [get-oncall]]
   [tcc.handlers.oncall.view :refer [oncall-view]]
   [tcc.layout :refer [application]]
   [tcc.models.util :refer [get-session-id]]))

(defn oncall
  [request]
  (let [title "Oncall"
        ok (get-session-id request)
        js nil
        rows (get-oncall)
        content (oncall-view title rows)]
    (application request title ok js content)))
