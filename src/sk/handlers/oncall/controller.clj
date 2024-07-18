(ns sk.handlers.oncall.controller
  (:require [sk.layout :refer [application]]
            [sk.models.util :refer [get-session-id]]
            [sk.handlers.oncall.model :refer [get-oncall]]
            [sk.handlers.oncall.view :refer [oncall-view]]))

(defn oncall [_]
  (let [title "Oncall"
        ok (get-session-id)
        js nil
        rows (get-oncall)
        content (oncall-view title rows)]
    (application title ok js content)))
