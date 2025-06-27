(ns tcc.handlers.admin.employees.view
  (:require [tcc.models.form :refer [form build-field build-modal-buttons]]
            [tcc.models.grid :refer [build-grid]]))

(defn employees-view
  [title rows]
  (let [labels ["Name" "Email" "Pphone" "Sphone"]
        db-fields [:name :email :pphone :sphone]
        fields (apply array-map (interleave db-fields labels))
        table-id "employees_table"
        href "/admin/employees"
        args {:new true :edit true :delete true}]
    (build-grid title rows table-id fields href args)))

(defn build-employees-fields
  [row]
  (list
   (build-field {:id "id" :type "hidden" :name "id" :value (:id row)})
   (build-field {:label "Name" :type "text" :id "name" :name "name" :placeholder "Name here..." :required true :value (get row :name)})
   (build-field {:label "Email" :type "email" :id "email" :name "email" :placeholder "Email here..." :required true :value (get row :email)})
   (build-field {:label "Primary Phone<br><small>Format:xxx-xxx-xxxx</small>" :type "tel" :id "pphone" :name "pphone" :placeholder "Pphone here..." :required true :value (get row :pphone)
                 :pattern "\\d{3}-\\d{3}-\\d{4}"})
   (build-field {:label "Secondary Phone<br><small>Format:xxx-xxx-xxxx</small>" :type "tel" :id "sphone" :name "sphone" :placeholder "Sphone here..." :required true :value (get row :sphone)
                 :pattern "\\d{3}-\\d{3}-\\d{4}"})))

(defn employees-form-view
  [title row]
  (form "/admin/employees/save" (build-employees-fields row) (build-modal-buttons) title {:bare true}))
