(ns sk.handlers.admin.rgroups.model
  (:require [sk.models.crud :refer [Query db]]
            [clojure.string :as st]))

(def get-rgroups-sql
  (str
   "
SELECT  rg.*,
        rgn.name as parent_formatted,
        CONCAT(e.name,' ',e.firstname) AS manager_id_formatted
FROM rgroups as rg
LEFT JOIN rgroups rgn ON rgn.id = rg.parent
LEFT JOIN employees AS e ON e.id = rg.manager_id
ORDER BY rg.name
"))

(defn get-rgroups
  []
  (Query db get-rgroups-sql))

(def get-rgroups-id-sql
  (str
   "
SELECT *
FROM rgroups
WHERE id = ?
"))

(defn get-rgroups-id
  [id]
  (first (Query db [get-rgroups-id-sql id])))

(defn parent-options
  []
  (let [rows (Query db "SELECT *  
                       FROM rgroups
                       ORDER BY name")
        options (map (fn [row]
                       {:value (:id row)
                        :label (:name row)}) rows)]
    (list*  {:value 0
             :label "No parent..."} options)))

(defn manager-options
  []
  (let [rows (Query db "SELECT *, CONCAT(name,' ',firstname) AS name_formatted  
                       FROM employees
                       WHERE is_manager='Y'
                       ORDER BY name")
        options (map (fn [row]
                       {:value (:id row)
                        :label (:name_formatted row)}) rows)]
    (list* {:value 0
            :label "No manager..."} options)))

(comment
  (parent-options)
  (manager-options))
