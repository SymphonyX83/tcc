(ns tcc.handlers.admin.detections.model
  (:require [tcc.models.crud :refer [Query db]]))

(def get-detections-sql
  (str "SELECT * FROM detections"))

(defn get-detections
  []
  (Query db get-detections-sql))

(defn get-detections-id
  [id]
  (first (Query db (str get-detections-sql " WHERE detections.id=" id))))
