(ns tcc.handlers.admin.detections.view
  (:require [tcc.models.form :refer [form build-field build-modal-buttons]]
            [tcc.models.grid :refer [build-grid]]))

(defn detections-view
  [title rows]
  (let [labels ["Name" "Description"]
        db-fields [:name :description]
        fields (apply array-map (interleave db-fields labels))
        table-id "detections_table"
        href "/admin/detections"
        args {:new true :edit true :delete true}]
    (build-grid title rows table-id fields href args)))

(defn build-detections-fields
  [row]
  (list
   (build-field {:id "id" :type "hidden" :name "id" :value (:id row)})
   (build-field {:label "Name" :type "text" :id "name" :name "name" :placeholder "Name here..." :required true :value (get row :name)})
   (build-field {:label "Description" :type "text" :id "description" :name "description" :placeholder "Description here..." :required false :value (get row :description)})))

(defn detections-form-view
  [title row]
  (form "/admin/detections/save" (build-detections-fields row) (build-modal-buttons) title {:bare true}))
