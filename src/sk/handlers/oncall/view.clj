(ns sk.handlers.oncall.view
  (:require [sk.models.grid :refer [build-dashboard]]))

(defn oncall-view
  [title rows]
  (let [table-id "oncall_table"
        labels ["GROUP" "MANAGER" "MGR CONTACT" "PRIMARY ONCALL" "PONCALL PHONES" "SECONDARY ONCALL" "SONCALL PHONES" "START" "END"]
        db-fields [:rgroup_id_formatted :manager_id_formatted :manager_phones :poncall_id_formatted :soncall_phones :soncall_id_formatted :soncall_phones :start_date :end_date]
        fields (zipmap db-fields labels)]
    (build-dashboard title rows table-id fields)))
