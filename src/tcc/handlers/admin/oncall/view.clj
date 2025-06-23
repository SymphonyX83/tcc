(ns tcc.handlers.admin.oncall.view
  (:require
   [tcc.handlers.admin.oncall.model :refer [get-poncall-options
                                            get-soncall-options
                                            get-manager-options
                                            get-rgroup-options]]
   [tcc.models.form :refer [build-field build-modal-buttons form]]
   [tcc.models.grid :refer [build-grid]]))

(defn oncall-view
  [title rows]
  (let [labels ["Rgroup id" "Primary id" "Secondary id" "Manager id" "Start oncall" "End oncall" "Comments"]
        db-fields [:rgroup_id_formatted :primary_id_formatted :secondary_id_formatted :manager_id_formatted :start_oncall :end_oncall :comments]
        fields (apply array-map (interleave db-fields labels))
        table-id "oncall_table"
        href "/admin/oncall"
        args {:new true :edit true :delete true}]
    (build-grid title rows table-id fields href args)))

(defn build-oncall-fields
  [row]
  (list
   (build-field {:id "id" :type "hidden" :name "id" :value (:id row)})
   (build-field {:label "Resolution Group" :type "select" :id "rgroup_id" :name "rgroup_id" :required true :value (get row :rgroup_id) :options (get-rgroup-options)})
   (build-field {:label "Primary Contact" :type "select" :id "primary_id" :name "primary_id" :required true :value (get row :primary_id) :options (get-poncall-options)})
   (build-field {:label "Backup Contact" :type "select" :id "secondary_id" :name "secondary_id" :required false :value (get row :secondary_id) :options (get-soncall-options)})
   (build-field {:label "Manager" :type "select" :id "manager_id" :name "manager_id" :required true :value (get row :manager_id) :options (get-manager-options)})
   (build-field {:label "Start Date" :type "date" :id "start_oncall" :name "start_oncall" :placeholder "Start oncall here..." :required true :value (get row :start_oncall)})
   (build-field {:label "End Date" :type "date" :id "end_oncall" :name "end_oncall" :placeholder "End oncall here..." :required true :value (get row :end_oncall)})
   (build-field {:label "Comments" :type "text" :id "comments" :name "comments" :placeholder "Comments here..." :required false :value (get row :comments)})))

(defn oncall-form-view
  [title row]
  (form "/admin/oncall/save" (build-oncall-fields row) (build-modal-buttons) title {:bare true}))
