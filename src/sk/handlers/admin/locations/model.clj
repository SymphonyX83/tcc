(ns sk.handlers.admin.locations.model
  (:require [sk.models.crud :refer [Query db]]
            [clojure.string :as st]))

(def get-locations-sql
  (str
   "
SELECT *
FROM locations
"))

(defn get-locations
  []
  (Query db get-locations-sql))

(def get-locations-id-sql
  (str
   "
SELECT *
FROM locations
WHERE id = ?
"))

(defn get-locations-id
  [id]
  (first (Query db [get-locations-id-sql id])))
