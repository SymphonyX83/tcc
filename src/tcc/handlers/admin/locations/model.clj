(ns tcc.handlers.admin.locations.model
  (:require [tcc.models.crud :refer [Query db]]))

(def get-locations-sql
  (str "SELECT * FROM locations"))

(defn get-locations
  []
  (Query db get-locations-sql))

(defn get-locations-id
  [id]
  (first (Query db (str get-locations-sql " WHERE locations.id=" id))))
