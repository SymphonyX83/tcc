(ns tcc.handlers.admin.rgroups.view
  (:require [tcc.models.form :refer [form build-field build-modal-buttons]]
            [tcc.models.grid :refer [build-grid]]))

(defn rgroups-view
  [title rows]
  (let [labels ["Name" "Email" "Description"]
        db-fields [:name :email :description]
        fields (apply array-map (interleave db-fields labels))
        table-id "rgroups_table"
        href "/admin/rgroups"
        args {:new true :edit true :delete true}]
    (build-grid title rows table-id fields href args)))

(defn build-rgroups-fields
  [row]
  (list
   (build-field {:id "id" :type "hidden" :name "id" :value (:id row)})
   (build-field {:label "Name" :type "text" :id "name" :name "name" :placeholder "Name here..." :required true :value (get row :name)})
   (build-field {:label "Email" :type "text" :id "email" :name "email" :placeholder "Email here..." :required false :value (get row :email)})
   (build-field {:label "Description" :type "text" :id "description" :name "description" :placeholder "Description here..." :required false :value (get row :description)})))

(defn rgroups-form-view
  [title row]
  (form "/admin/rgroups/save" (build-rgroups-fields row) (build-modal-buttons) title {:bare true}))
