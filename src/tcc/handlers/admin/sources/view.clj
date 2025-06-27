(ns tcc.handlers.admin.sources.view
  (:require
   [tcc.models.form :refer [build-field build-modal-buttons form]]
   [tcc.models.grid :refer [build-grid]]
   [tcc.models.util :refer [get-options]]))

(defn sources-view
  [title rows]
  (let [labels ["Detection" "Name" "Description"]
        db-fields [:detection_name :name :description]
        fields (apply array-map (interleave db-fields labels))
        table-id "sources_table"
        href "/admin/sources"
        args {:new true :edit true :delete true}]
    (build-grid title rows table-id fields href args)))

(defn build-sources-fields
  [row]
  (list
   (build-field {:id "id" :type "hidden" :name "id" :value (:id row)})
   (build-field {:label "Detection" :type "select" :id "detection_id" :name "detection_id" :placeholder "Detection id here..." :required true :value (get row :detection_id)
                 :options (get-options "detections" "id" "name")})
   (build-field {:label "Name" :type "text" :id "name" :name "name" :placeholder "Name here..." :required true :value (get row :name)})
   (build-field {:label "Description" :type "text" :id "description" :name "description" :placeholder "Description here..." :required false :value (get row :description)})))

(defn sources-form-view
  [title row]
  (form "/admin/sources/save" (build-sources-fields row) (build-modal-buttons) title {:bare true}))
