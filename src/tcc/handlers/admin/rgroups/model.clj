(ns tcc.handlers.admin.rgroups.model
  (:require [tcc.models.crud :refer [Query db]]))

(def get-rgroups-sql
  (str "SELECT * FROM rgroups"))

(defn get-rgroups
  []
  (Query db get-rgroups-sql))

(defn get-rgroups-id
  [id]
  (first (Query db (str get-rgroups-sql " WHERE rgroups.id=" id))))
