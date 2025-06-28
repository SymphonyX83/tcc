(ns tcc.handlers.admin.incidents.model
  (:require [tcc.models.crud :refer [Query db]]))

(def get-incidents-sql
  (str "SELECT i.*,
        date_format(i.start_time, '%h:%i %p') as start_time_formatted,
        date_format(i.end_time, '%h:%i %p') as end_time_formatted,
        case when i.status = 1 then 'Open'
        else 'Closed' end as status_formatted,
        l.name as location_name,
        concat(s.name,' ',d.name) as source_name,
        g.name as group_name,
        e.name as coordinator1_name,
        e2.name as coordinator2_name,
        e3.name as coordinator3_name
        FROM incidents i
        LEFT JOIN sources s ON i.source_id = s.id
        LEFT JOIN detections d ON s.detection_id = d.id
        LEFT JOIN locations l ON i.location_id = l.id
        LEFT JOIN groups g ON i.group_id = g.id
        LEFT JOIN employees e ON i.coordinator1_id = e.id
        LEFT JOIN employees e2 ON i.coordinator2_id = e2.id
        LEFT JOIN employees e3 ON i.coordinator3_id = e3.id
        "))

(defn get-incidents
  []
  (Query db get-incidents-sql))

(defn get-incidents-id
  [id]
  (first (Query db (str "SELECT * from incidents WHERE id=" id))))

(defn sources-options
  []
  (Query db "SELECT 
             s.id as value, 
             concat(d.name,' ',s.name) as label 
             FROM sources s
             join detections d on s.detection_id = d.id
             ORDER BY s.name"))

(comment
  (sources-options)
  (get-incidents))