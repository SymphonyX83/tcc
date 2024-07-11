(ns sk.handlers.admin.employees.model
  (:require [sk.models.crud :refer [Query db]]
            [clojure.string :as st]))

(def get-employees-sql
  (str
   "
   SELECT e.*,
          rg.name as rgroup_id_formatted,
          CONCAT(l.name,', ',l.city) as location_id_formatted
   FROM employees AS e 
   JOIN rgroups AS rg ON rg.id=e.rgroup_id
   JOIN locations AS l ON l.id=e.location_id
   ORDER BY e.name
"))

(defn get-employees
  []
  (Query db get-employees-sql))

(def get-employees-id-sql
  (str
   "
SELECT *
FROM employees
WHERE id = ?
"))

(defn get-employees-id
  [id]
  (first (Query db [get-employees-id-sql id])))

(defn rgroup-options
  []
  (let [rows (Query db "SELECT * 
                       FROM rgroups
                       ORDER BY name")
        options (map (fn [row]
                       {:value (:id row)
                        :label (:name row)}) rows)]
    (list* {:value "0"
            :label "Select Resolution Group..."} options)))

(defn location-options
  []
  (let [rows (Query db "SELECT *, CONCAT(name,', ',city) AS name_formatted  
                       FROM locations
                       ORDER BY name")
        options (map (fn [row]
                       {:value (:id row)
                        :label (:name_formatted row)}) rows)]
    (list* {:value ""
            :label "Select a location..."} options)))

(comment
  (rgroup-options)
  (location-options)
  (get-employees))
