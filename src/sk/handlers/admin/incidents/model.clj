(ns sk.handlers.admin.incidents.model
  (:require [sk.models.crud :refer [Query db]]
            [clojure.string :as st]))

(def get-incidents-sql
  (str
   "
SELECT *
FROM incidents
"))

(defn get-incidents
  []
  (Query db get-incidents-sql))

(def get-incidents-id-sql
  (str
   "
SELECT *
FROM incidents
WHERE id = ?
"))

(defn get-incidents-id
  [id]
  (first (Query db [get-incidents-id-sql id])))
