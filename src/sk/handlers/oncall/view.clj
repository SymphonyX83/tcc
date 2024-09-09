(ns sk.handlers.oncall.view
  (:require [sk.models.grid :refer [build-dashboard]]))

(defn oncall-view
  [title rows]
  (let [table-id "oncall_table"
        labels ["GROUP" "PRIMARY ONCALL" "PHONES" "SECONDARY ONCALL" "PHONES" "MANAGER" "MGR CONTACT"]
        db-fields [:rgroup_id_formatted :poncall_id_formatted :poncall_phones :soncall_id_formatted :soncall_phones :manager_id_formatted :manager_phones]
        fields (zipmap db-fields labels)]
    (build-dashboard title rows table-id fields)))
