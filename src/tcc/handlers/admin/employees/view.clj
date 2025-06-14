(ns tcc.handlers.admin.employees.view
  (:require
   [tcc.models.form :refer [build-field build-modal-buttons form]]
   [tcc.models.grid :refer [build-grid]]))

(defn employees-view
  [title rows]
  (let [labels ["Firstname" "Lastname" "Primary Phone" "Secondary Phone" "Manager"]
        db-fields [:firstname :lastname :pphone :sphone :is_manager_formatted]
        fields (apply array-map (interleave db-fields labels))
        table-id "employees_table"
        href "/admin/employees"
        args {:new true :edit true :delete true}]
    (build-grid title rows table-id fields href args)))

(defn build-employees-fields
  [row]
  (list
   (build-field {:id "id" :type "hidden" :name "id" :value (:id row)})
   (build-field {:label "Firstname" :type "text" :id "firstname" :name "firstname" :placeholder "Firstname here..." :required true :value (get row :firstname)})
   (build-field {:label "Lastname" :type "text" :id "lastname" :name "lastname" :placeholder "Lastname here..." :required true :value (get row :lastname)})
   (build-field {:label "Primary phone" :type "text" :id "pphone" :name "pphone" :placeholder "Pphone here..." :required true :value (get row :pphone)})
   (build-field {:label "Secondary phone" :type "text" :id "sphone" :name "sphone" :placeholder "Sphone here..." :required false :value (get row :sphone)})
   (build-field {:label "Is manager?" :type "select" :id "is_manager" :name "is_manager" :required true :value (get row :is_manager) :options [{:value "N" :label "No"} {:value "Y" :label "Yes"}]})))

(defn employees-form-view
  [title row]
  (form "/admin/employees/save" (build-employees-fields row) (build-modal-buttons) title {:bare true}))
