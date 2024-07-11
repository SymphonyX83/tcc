(ns sk.handlers.admin.sources.model
  (:require [sk.models.crud :refer [Query db]]
            [clojure.string :as st]))

(def get-sources-sql
  (str
   "
SELECT *
FROM sources
"))

(defn get-sources
  []
  (Query db get-sources-sql))

(def get-sources-id-sql
  (str
   "
SELECT *
FROM sources
WHERE id = ?
"))

(defn get-sources-id
  [id]
  (first (Query db [get-sources-id-sql id])))
