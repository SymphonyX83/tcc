(ns tcc.handlers.admin.oncall.view
  (:require
   [tcc.models.form :refer [build-field build-modal-buttons form]]
   [tcc.models.grid :refer [build-grid]]
   [tcc.models.util :refer [get-options]]))

(defn oncall-view
  [title rows]
  (let [labels ["Group" "Primary employee" "Secondary employee" "Manager" "Start oncall" "End oncall" "Comments"]
        db-fields [:groups_name :primary_employee :secondary_employee :manager :start_oncall :end_oncall :comments]
        fields (apply array-map (interleave db-fields labels))
        table-id "oncall_table"
        href "/admin/oncall"
        args {:new true :edit true :delete true}]
    (build-grid title rows table-id fields href args)))

(defn build-oncall-fields
  [row]
  (list
   (build-field {:id "id" :type "hidden" :name "id" :value (:id row)})
   (build-field {:label "Group" :type "select" :id "groups_id" :name "groups_id" :placeholder "Rgroups id here..." :required true :value (get row :rgroups_id)
                 :options (get-options "groups" "id" "name")})
   (build-field {:label "Primary Employee" :type "select" :id "primary_employee_id" :name "primary_employee_id" :placeholder "Primary employee id here..." :required true :value (get row :primary_employee_id)
                 :options (get-options "employees" "id" "name")})
   (build-field {:label "Secondary Employee" :type "select" :id "secondary_employee_id" :name "secondary_employee_id" :placeholder "Secondary employee id here..." :required true :value (get row :secondary_employee_id)
                 :options (get-options "employees" "id" "name")})
   (build-field {:label "Manager" :type "select" :id "manager_employee_id" :name "manager_employee_id" :placeholder "Manager employee id here..." :required true :value (get row :manager_employee_id)
                 :options (get-options "employees" "id" "name")})
   (build-field {:label "Start oncall" :type "date" :id "start_oncall" :name "start_oncall" :placeholder "Start oncall here..." :required true :value (get row :start_oncall)})
   (build-field {:label "End oncall" :type "date" :id "end_oncall" :name "end_oncall" :placeholder "End oncall here..." :required true :value (get row :end_oncall)})
   (build-field {:label "Comments" :type "textarea" :id "comments" :name "comments" :placeholder "Comments here..." :required false :value (get row :comments)
                 :rows "5"})))

(defn oncall-form-view
  [title row]
  (form "/admin/oncall/save" (build-oncall-fields row) (build-modal-buttons) title {:bare true}))
