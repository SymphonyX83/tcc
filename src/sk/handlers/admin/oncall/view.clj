(ns sk.handlers.admin.oncall.view
  (:require [ring.util.anti-forgery :refer [anti-forgery-field]]
            [sk.models.form :refer [form build-hidden-field build-field build-select build-radio build-modal-buttons build-textarea]]
            [sk.handlers.admin.oncall.model :refer [rgroup-options oncall-options]]
            [sk.models.grid :refer [build-grid build-modal modal-script]]))

(defn oncall-view
  [title rows]
  (let [labels ["RGROUP_ID" "PONCALL_ID" "SONCALL_ID" "START_DATE" "END_DATE"]
        db-fields [:rgroup_id_formatted :poncall_id_formatted :soncall_id_formatted :start_date :end_date]
        fields (zipmap db-fields labels)
        table-id "oncall_table"
        args {:new true :edit true :delete true}
        href "/admin/oncall"]
    (build-grid title rows table-id fields href args)))

(defn build-oncall-fields
  [row]
  (list
   (build-hidden-field {:id "id"
                        :name "id"
                        :value (:id row)})
   (build-select {:label "RGROUP_ID"
                  :id "rgroup_id"
                  :name "rgroup_id"
                  :required false
                  :value (:rgroup_id row)
                  :options (rgroup-options)})
   (build-select {:label "PONCALL_ID"
                  :id "poncall_id"
                  :name "poncall_id"
                  :required false
                  :value (:poncall_id row)
                  :options (oncall-options)})
   (build-select {:label "SONCALL_ID"
                  :id "soncall_id"
                  :name "soncall_id"
                  :required false
                  :value (:soncall_id row)
                  :options (oncall-options)})
   (build-field {:label "START_DATE"
                 :type "date"
                 :id "start_date"
                 :name "start_date"
                 :placeholder "start_date aqui..."
                 :required false
                 :value (:start_date row)})
   (build-field {:label "END_DATE"
                 :type "date"
                 :id "end_date"
                 :name "end_date"
                 :placeholder "end_date aqui..."
                 :required false
                 :value (:end_date row)})))

(defn build-oncall-form
  [title row]
  (let [fields (build-oncall-fields row)
        href "/admin/oncall/save"
        buttons (build-modal-buttons)]
    (form href fields buttons)))

(defn build-oncall-modal
  [title row]
  (build-modal title row (build-oncall-form title row)))

(defn oncall-edit-view
  [title row rows]
  (list
   (oncall-view "oncall Manteniento" rows)
   (build-oncall-modal title row)))

(defn oncall-add-view
  [title row rows]
  (list
   (oncall-view "oncall Mantenimiento" rows)
   (build-oncall-modal title row)))

(defn oncall-modal-script
  []
  (modal-script))
