(ns tcc.handlers.admin.sources.view
  (:require
   [tcc.handlers.admin.sources.model :refer [detection-options]]
   [tcc.models.form :refer [form build-field build-modal-buttons]]
   [tcc.models.grid :refer [build-grid]]))

(defn sources-view
  [title rows]
  (let [labels ["Type of Detection" "Name" "Description"]
        db-fields [:detection_id_formatted :name :description]
        fields (apply array-map (interleave db-fields labels))
        table-id "sources_table"
        href "/admin/sources"
        args {:new true :edit true :delete true}]
    (build-grid title rows table-id fields href args)))

(defn build-sources-fields
  [row]
  (list
   (build-field {:id "id" :type "hidden" :name "id" :value (:id row)})
   (build-field {:label "Type of Detection" :type "select" :id "detection_id" :name "detection_id" :required true :value (get row :detection_id) :options (detection-options)})
   (build-field {:label "Name" :type "text" :id "name" :name "name" :placeholder "Name here..." :required false :value (get row :name)})
   (build-field {:label "Description" :type "text" :id "description" :name "description" :placeholder "Description here..." :required false :value (get row :description)})))

(defn sources-form-view
  [title row]
  (form "/admin/sources/save" (build-sources-fields row) (build-modal-buttons) title {:bare true}))
