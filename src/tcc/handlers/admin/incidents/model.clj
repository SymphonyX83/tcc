(ns tcc.handlers.admin.incidents.model
  (:require [tcc.models.crud :refer [Query db]]
            [clojure.java.jdbc :as sql]))

(def get-incidents-sql
  (str "SELECT * FROM incidents"))

(def get-incidents-display-sql
  (str
   "SELECT inc.*,
    l.name AS location_id_formatted,
    rg.name AS rgroup_id_formatted,
    COALESCE(
        CONCAT(e1.firstname,' ',e1.lastname, | ,e2.firstname,' ',e2.lastname, | ,e3.firstname,' ',e3.lastname)) AS coordinators
    FROM incidents AS inc
    LEFT JOIN locations AS l ON l.id = inc.location_id
    LEFT JOIN rgroups AS rg ON rg.id = rgroup_id
    LEFT JOIN employees AS e1 ON e1.id = inc.coordinator1_id
    LEFT JOIN employees AS e2 ON e1.id = inc.coordinator2_id
    LEFT JOIN employees AS e3 ON e1.id = inc.coordinator3_id
    ORDER BY incidents.status DESC

    "))

(defn get-incidents
  []
  (Query db get-incidents-display-sql))

(defn get-incidents-id
  [id]
  (first (Query db (str get-incidents-sql " WHERE incidents.id=" id))))


(comment
  (get-incidents))