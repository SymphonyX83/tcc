(ns tcc.handlers.incidents.controller
  (:require
   [tcc.handlers.incidents.model :refer [get-incidents]]
   [tcc.handlers.incidents.view :refer [incidents-view]]
   [tcc.layout :refer [application]]
   [tcc.models.util :refer [get-session-id]]))

(defn incidents
  [request]
  (let [title "Incidents"
        ok (get-session-id request)
        js nil
        rows (get-incidents)
        content (incidents-view title rows)]
    (application request title ok js content)))
