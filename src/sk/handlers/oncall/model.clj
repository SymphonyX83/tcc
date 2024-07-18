(ns sk.handlers.oncall.model
  (:require [sk.models.crud :refer [Query db]]
            [clojure.string :as st]))

(def get-oncall-sql
  (str
   "
SELECT  o.*,
        rg.name AS rgroup_id_formatted,
        CONCAT(e1.name,' ',e1.firstname) AS poncall_id_formatted,
        CONCAT(e1.pphone,' - ',e1.sphone) AS poncall_phones,
        CONCAT(e2.name,' ',e2.firstname) AS soncall_id_formatted,
        CONCAT(e2.pphone,' - ',e2.sphone) AS soncall_phones,
        CONCAT(e3.name,' ',e3.firstname) AS manager_id_formatted,
        CONCAT(e3.pphone,' - ',e3.sphone) AS manager_phones
FROM oncall AS o
JOIN rgroups as rg ON o.rgroup_id = rg.id
JOIN employees AS e1 ON o.poncall_id = e1.id
JOIN employees AS e2 ON o.soncall_id = e2.id
JOIN employees AS e3 ON rg.manager_id = e3.id
"))

(defn get-oncall
  []
  (Query db get-oncall-sql))

(def get-oncall-id-sql
  (str
   "
SELECT *
FROM oncall
WHERE id = ?
"))

(defn get-oncall-id
  [id]
  (first (Query db [get-oncall-id-sql id])))

(comment
  (get-oncall-id 1)
  (get-rgroup-id 5))
