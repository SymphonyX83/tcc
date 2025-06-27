(ns tcc.handlers.admin.employees.model
  (:require [tcc.models.crud :refer [Query db]]))

(def get-employees-sql
  (str "SELECT * FROM employees"))

(defn get-employees
  []
  (Query db get-employees-sql))

(defn get-employees-id
  [id]
  (first (Query db (str get-employees-sql " WHERE employees.id=" id))))
