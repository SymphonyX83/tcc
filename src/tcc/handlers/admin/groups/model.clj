(ns tcc.handlers.admin.groups.model
  (:require [tcc.models.crud :refer [Query db]]))

(def get-groups-sql
  (str "SELECT * FROM groups"))

(defn get-groups
  []
  (Query db get-groups-sql))

(defn get-groups-id
  [id]
  (first (Query db (str get-groups-sql " WHERE groups.id=" id))))
