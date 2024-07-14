(ns sk.handlers.admin.incidents.model
  (:require [sk.models.crud :refer [Query db]]
            [clojure.string :as st]))

(def get-incidents-sql
  (str
   "
SELECT  i.*,
        rg.name AS rgroup_id_formatted, 
        CONCAT(e1.name,' ',e1.firstname,'/',e2.name,' ',e2.firstname,'/',e3.name,' ',e3.firstname) AS coord_id_1_formatted,
        src.name AS source_id_formatted,
        CASE 
            WHEN i.status='O' THEN 'Open'
            WHEN i.status='C' THEN 'Completed'
        END AS status_formatted,
        CASE
            WHEN i.severity=1 THEN '1=Critical'
            WHEN i.severity=2 THEN '2=High'
            WHEN i.severity=3 THEN '3=Moderate'
        END AS severity_formatted
FROM incidents AS i
JOIN rgroups AS rg ON i.rgroup_id=rg.id 
JOIN sources AS src ON i.source_id=src.id
JOIN employees AS e1 ON i.coord_id_1=e1.id
JOIN employees AS e2 ON i.coord_id_2=e2.id
JOIN employees AS e3 ON i.coord_id_3=e3.id
"))

(defn get-incidents
  []
  (Query db get-incidents-sql))

(def get-incidents-id-sql
  (str
   "
SELECT *
FROM incidents
WHERE id = ?
"))

(defn get-incidents-id
  [id]
  (first (Query db [get-incidents-id-sql id])))

(defn rgroup-options
  []
  (let [rows (Query db "SELECT *, name AS name_formatted
                        FROM rgroups
                        ORDER BY name ")
        options (map (fn [row]
                       {:value (:id row)
                        :label (:name_formatted row)}) rows)]
    (list* {:value ""
            :label "Select a resolution group..."} options)))

(defn source-options
  []
  (let [rows (Query db "SELECT *, name AS name_formatted
                        FROM sources
                        ORDER BY name ")
        options (map (fn [row]
                       {:value (:id row)
                        :label (:name_formatted row)}) rows)]
    (list* {:value ""
            :label "Select a source for this incident..."} options)))

(defn coord-options
  []
  (let [rows (Query db "SELECT *, 
                       CONCAT(name,' ',firstname) AS name_formatted
                        FROM employees
                        ORDER BY firstname ")
        options (map (fn [row]
                       {:value (:id row)
                        :label (:name_formatted row)}) rows)]
    (list* {:value ""
            :label "Select a coordinator..."} options)))

(comment
  (get-incidents))
