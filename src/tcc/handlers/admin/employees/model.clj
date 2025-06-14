(ns tcc.handlers.admin.employees.model
  (:require
   [tcc.models.crud :refer [db Query]]))

(def get-employees-sql
  (str "SELECT emp.*,
        CASE WHEN emp.is_manager = 'Y' THEN 'YES' ELSE 'NO' END AS is_manager_formatted
        FROM employees AS emp
        ORDER BY emp.firstname, emp.lastname"))

(defn get-employees
  []
  (Query db get-employees-sql))

(defn get-employees-id
  [id]
  (first (Query db (str get-employees-sql " WHERE employees.id=" id))))

(comment
  (get-employees))
