(ns tcc.handlers.admin.sources.model
  (:require [tcc.models.crud :refer [Query db]]))

(def get-sources-sql
  (str "SELECT s.*,
        d.name AS detection_name
        FROM sources s
        LEFT JOIN detections d ON s.detection_id = d.id
        "))

(defn get-sources
  []
  (Query db get-sources-sql))

(defn get-sources-id
  [id]
  (first (Query db (str "select * from sources WHERE id=" id))))

(comment
  (get-sources))