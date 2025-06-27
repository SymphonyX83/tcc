(ns tcc.handlers.oncall.model
  (:require [tcc.models.crud :refer [Query db]]))

(def get-oncall-sql
  (str "SELECT o.*,
        g.name AS groups_name,
        p.name AS primary_employee,
        s.name AS secondary_employee,
        m.name AS manager
        FROM oncall o
        left join groups g on o.groups_id = g.id
        left join employees p on o.primary_employee_id = p.id
        left join employees s on o.secondary_employee_id = s.id
        left join employees m on o.manager_employee_id = m.id
        where g.active = 'Yes'
        "))

(defn get-oncall
  []
  (Query db get-oncall-sql))

(comment
  (get-oncall))