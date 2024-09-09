(ns sk.handlers.oncall.model
  (:require [sk.models.crud :refer [Query db]]
            [clojure.string :as st]))

; (def get-oncall-sql
;   (str
;    "
;     SELECT o.*,
;           rg.name AS rgroup_id_formatted,
;           CONCAT(e1.name,' ',e1.firstname) AS poncall_id_formatted,
;           CONCAT(e1.pphone,' - ',e1.sphone) AS poncall_phones,
;           CONCAT(e2.name,' ',e2.firstname) AS soncall_id_formatted,
;           CONCAT(e2.pphone,' - ',e2.sphone) AS soncall_phones,
;           CONCAT(e3.name,' ',e3.firstname) AS manager_id_formatted,
;           CONCAT(e3.pphone,' - ',e3.sphone) AS manager_phones
;     FROM oncall AS o
;     JOIN rgroups AS rg ON o.rgroup_id = rg.id
;     JOIN employees AS e1 ON o.poncall_id = e1.id
;     JOIN employees AS e2 ON o.soncall_id = e2.id
;     JOIN employees AS e3 ON rg.manager_id = e3.id
;     "))

(def get-oncall-sql
  (str
   "
    SELECT o.*,
          rg.manager_id,
          rg.name AS rgroup_id_formatted
    FROM oncall AS o
    JOIN rgroups AS rg ON o.rgroup_id = rg.id
   "))

(defn get-oncall-extras
  [employee-id]
  (let [row (first (Query db ["SELECT name,firstname,pphone,sphone from employees where id = ?" employee-id]))
        name (or (:name row) "")
        firstname (or (:firstname row) "")
        pphone (or (:pphone row) " ")
        sphone (or (:sphone row) " ")
        names (str name " - " firstname)
        phones (str pphone " - " sphone)]
    [names phones]))

(defn get-oncall
  []
  (let [orows (Query db get-oncall-sql)
        rows (map (fn [row]
                    (let [poncall (get-oncall-extras (:poncall_id row))
                          soncall (get-oncall-extras (:soncall_id row))
                          manager (get-oncall-extras (:manager_id row))
                          row (assoc row
                                     :poncall_id_formatted (get poncall 0)
                                     :poncall_phones (get poncall 1)
                                     :soncall_id_formatted (get soncall 0)
                                     :soncall_phones (get soncall 1)
                                     :manager_id_formatted (get manager 0)
                                     :manager_phones (get manager 1))] row)) orows)]
    rows))

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
  (get-oncall-extras 2)
  (Query db get-oncall-sql)
  (get-oncall)
  (get-rgroup-id 5))
