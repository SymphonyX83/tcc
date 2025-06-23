(ns tcc.handlers.admin.oncall.model
  (:require [tcc.models.crud :refer [Query db]]))

(def get-oncall-display-sql
  (str "SELECT oncall.*,
        rgroups.name AS rgroup_id_formatted,
        COALESCE(CONCAT(e1.firstname, ' ', e1.lastname)) AS primary_id_formatted,
        IF(COALESCE(CONCAT(e2.firstname, ' ', e2.lastname)),'0','< No Backup >') AS secondary_id_formatted,
        COALESCE(CONCAT(e3.firstname, ' ', e3.lastname)) AS manager_id_formatted
       FROM oncall
        LEFT JOIN rgroups ON oncall.rgroup_id = rgroups.id
        LEFT JOIN employees AS e1 ON oncall.primary_id = e1.id
        LEFT JOIN employees AS e2 ON oncall.secondary_id = e2.id
        LEFT JOIN employees AS e3 ON oncall.manager_id = e3.id
        ORDER BY oncall.rgroup_id ASC"))

(def get-oncall-sql
  (str
   "SELECT * FROM oncall"))

(defn get-oncall
  []
  (Query db get-oncall-display-sql))

(defn get-oncall-id
  [id]
  (first (Query db (str get-oncall-sql " WHERE oncall.id=" id))))

(def get-rgroup-options-sql
  (str
   "
    SELECT 
    rgroups.id AS value,
    rgroups.name as label
    FROM rgroups
    ORDER BY rgroups.name
    "))

(defn get-rgroup-options
  []
  (Query db get-rgroup-options-sql))

(def get-poncall-options-sql
  (str
   "
    SELECT 
    employees.id AS value,
    COALESCE(CONCAT(employees.firstname,' ',employees.lastname)) AS label
    FROM employees
    ORDER BY employees.lastname"))

(defn get-poncall-options
  []
  (Query db get-poncall-options-sql))

(def get-soncall-options-sql
  (str
   "
    SELECT 
    employees.id AS value,
    COALESCE(CONCAT(employees.firstname,' ',employees.lastname)) AS label
    FROM employees
   ORDER BY employees.lastname "))

(defn get-soncall-options
  []
  (let [rows (Query db get-soncall-options-sql)]
    (list* {:value "0" :label " <<No Backup Contact>> "} rows)))


(def get-manager-options-sql
  (str
   "
   SELECT
   employees.id AS value,
   COALESCE (CONCAT (employees.firstname,' ',employees.lastname)) AS label
   FROM employees
   WHERE employees.is_manager = 'Y'
   ORDER BY employees.lastname "))

(defn get-manager-options
  []
  (Query db get-manager-options-sql))

(comment
  (get-rgroup-options)
  (get-poncall-options)
  (get-soncall-options)
  (get-manager-options))
