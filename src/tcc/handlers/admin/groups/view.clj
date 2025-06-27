(ns tcc.handlers.admin.groups.view
  (:require [tcc.models.form :refer [form build-field build-modal-buttons]]
            [tcc.models.grid :refer [build-grid]]))

(defn groups-view
  [title rows]
  (let [labels ["Name" "Email" "Description" "Active"]
        db-fields [:name :email :description :active]
        fields (apply array-map (interleave db-fields labels))
        table-id "groups_table"
        href "/admin/groups"
        args {:new true :edit true :delete true}]
    (build-grid title rows table-id fields href args)))

(defn build-groups-fields
  [row]
  (list
   (build-field {:id "id" :type "hidden" :name "id" :value (:id row)})
   (build-field {:label "Name" :type "text" :id "name" :name "name" :placeholder "Name here..." :required true :value (get row :name)})
   (build-field {:label "Email" :type "email" :id "email" :name "email" :placeholder "Email here..." :required true :value (get row :email)})
   (build-field {:label "Description" :type "text" :id "description" :name "description" :placeholder "Description here..." :required true :value (get row :description)})
   (build-field {:label "Active" :type "select" :id "active" :name "active" :placeholder "Active here..." :required true :value (get row :active)
                 :options [{:value "Yes" :label "Yes"}
                           {:value "No" :label "No"}]})))

(defn groups-form-view
  [title row]
  (form "/admin/groups/save" (build-groups-fields row) (build-modal-buttons) title {:bare true}))
