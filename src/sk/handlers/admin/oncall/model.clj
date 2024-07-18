(ns sk.handlers.admin.oncall.model
  (:require [sk.models.crud :refer [Query db]]
            [clojure.string :as st]))

(def get-oncall-sql
  (str
   "
SELECT  o.*,
        rg.name AS rgroup_id_formatted,
        CONCAT(e1.name,' ',e1.firstname) AS poncall_id_formatted,
        CONCAT(e2.name,' ',e2.firstname) AS soncall_id_formatted
FROM oncall AS o
JOIN rgroups as rg ON o.rgroup_id = rg.id
JOIN employees AS e1 ON o.poncall_id = e1.id
JOIN employees AS e2 ON o.soncall_id = e2.id
"))

(defn get-oncall
  []
  (Query db get-oncall-sql))

(def get-oncall-id-sql
  (str
   "
SELECT *
FROM oncall
WHERE id = ?
"))

(defn get-oncall-id
  [id]
  (first (Query db [get-oncall-id-sql id])))

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

(defn oncall-options
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
  ())
