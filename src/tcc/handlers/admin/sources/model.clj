(ns tcc.handlers.admin.sources.model
  (:require [tcc.models.crud :refer [Query db]]))

(def get-sources-sql
  (str "SELECT *
        FROM sources
       "))
(def get-sources-options-sql
  (str "SELECT sources.*,
               detections.name AS detection_id_formatted
               FROM sources
               LEFT JOIN detections ON sources.detection_id = detections.id
               ORDER BY detections.name ASC"))

(defn get-sources
  []
  (Query db get-sources-options-sql))

(defn get-sources-id
  [id]
  (first (Query db (str get-sources-sql " WHERE sources.id=" id))))

(def detection-options-sql
  (str
   "SELECT detections.id as value, detections.name as label FROM detections
     ORDER BY detections.name ASC"))

(defn detection-options
  []
  (Query db detection-options-sql))

(comment
  (get-sources)
  (get-sources-id 1)
  (detection-options))
