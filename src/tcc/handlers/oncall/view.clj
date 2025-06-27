(ns tcc.handlers.oncall.view
  (:require [tcc.models.grid :refer [build-dashboard]]))

(defn oncall-view
  [title rows]
  (let [labels ["group" "Primary employee" "Secondary employee" "Manager" "Start oncall" "End oncall" "Comments"]
        db-fields [:groups_name :primary_employee :secondary_employee :manager :start_oncall :end_oncall :comments]
        fields (apply array-map (interleave db-fields labels))
        table-id "oncall_table"]
    (build-dashboard title rows table-id fields)))
