(ns sk.handlers.admin.incidents.view
  (:require [ring.util.anti-forgery :refer [anti-forgery-field]]
            [sk.models.form :refer [form build-hidden-field build-field build-select build-radio build-modal-buttons build-textarea]]
            [sk.models.grid :refer [build-grid build-modal modal-script]]))

(defn incidents-view
  [title rows]
  (let [labels ["STATUS" "TITLE" "SEVERITY" "RGROUP_ID" "SOURCE_ID" "COORD_ID_1" "COORD_ID_2" "COORD_ID_3"]
        db-fields [:status :title :severity :rgroup_id :source_id :coord_id_1 :coord_id_2 :coord_id_3]
        fields (zipmap db-fields labels)
        table-id "incidents_table"
        args {:new true :edit true :delete true}
        href "/admin/incidents"]
    (build-grid title rows table-id fields href args)))

(defn build-incidents-fields
  [row]
  (list
   (build-hidden-field {:id "id"
                        :name "id"
                        :value (:id row)})
   (build-field {:label "STATUS"
                 :type "text"
                 :id "status"
                 :name "status"
                 :placeholder "status aqui..."
                 :required false
                 :value (:status row)})
   (build-field {:label "TITLE"
                 :type "text"
                 :id "title"
                 :name "title"
                 :placeholder "title aqui..."
                 :required false
                 :value (:title row)})
   (build-field {:label "INC_NUMBER"
                 :type "text"
                 :id "inc_number"
                 :name "inc_number"
                 :placeholder "inc_number aqui..."
                 :required false
                 :value (:inc_number row)})
   (build-field {:label "TIER"
                 :type "text"
                 :id "tier"
                 :name "tier"
                 :placeholder "tier aqui..."
                 :required false
                 :value (:tier row)})
   (build-field {:label "SEVERITY"
                 :type "text"
                 :id "severity"
                 :name "severity"
                 :placeholder "severity aqui..."
                 :required false
                 :value (:severity row)})
   (build-field {:label "ENVIRONMENT"
                 :type "text"
                 :id "environment"
                 :name "environment"
                 :placeholder "environment aqui..."
                 :required false
                 :value (:environment row)})
   (build-field {:label "RGROUP_ID"
                 :type "text"
                 :id "rgroup_id"
                 :name "rgroup_id"
                 :placeholder "rgroup_id aqui..."
                 :required false
                 :value (:rgroup_id row)})
   (build-field {:label "SOURCE_ID"
                 :type "text"
                 :id "source_id"
                 :name "source_id"
                 :placeholder "source_id aqui..."
                 :required false
                 :value (:source_id row)})
   (build-textarea {:label "SUMMARY"
                    :id "summary"
                    :name "summary"
                    :rows "3"
                    :placeholder "summary aqui..."
                    :required false
                    :value (:summary row)})
   (build-textarea {:label "CURRENT_STATUS"
                    :id "current_status"
                    :name "current_status"
                    :rows "3"
                    :placeholder "current_status aqui..."
                    :required false
                    :value (:current_status row)})
   (build-field {:label "POTENTIAL_ESCALATION"
                 :type "text"
                 :id "potential_escalation"
                 :name "potential_escalation"
                 :placeholder "potential_escalation aqui..."
                 :required false
                 :value (:potential_escalation row)})
   (build-textarea {:label "BRIDGE_DETAILS"
                    :id "bridge_details"
                    :name "bridge_details"
                    :rows "3"
                    :placeholder "bridge_details aqui..."
                    :required false
                    :value (:bridge_details row)})
   (build-field {:label "START_TIME"
                 :type "text"
                 :id "start_time"
                 :name "start_time"
                 :placeholder "start_time aqui..."
                 :required false
                 :value (:start_time row)})
   (build-field {:label "END_TIME"
                 :type "text"
                 :id "end_time"
                 :name "end_time"
                 :placeholder "end_time aqui..."
                 :required false
                 :value (:end_time row)})
   (build-field {:label "COORD_ID_1"
                 :type "text"
                 :id "coord_id_1"
                 :name "coord_id_1"
                 :placeholder "coord_id_1 aqui..."
                 :required false
                 :value (:coord_id_1 row)})
   (build-field {:label "COORD_ID_2"
                 :type "text"
                 :id "coord_id_2"
                 :name "coord_id_2"
                 :placeholder "coord_id_2 aqui..."
                 :required false
                 :value (:coord_id_2 row)})
   (build-field {:label "COORD_ID_3"
                 :type "text"
                 :id "coord_id_3"
                 :name "coord_id_3"
                 :placeholder "coord_id_3 aqui..."
                 :required false
                 :value (:coord_id_3 row)})
   (build-field {:label "TOTAL_OUTAGE"
                 :type "text"
                 :id "total_outage"
                 :name "total_outage"
                 :placeholder "total_outage aqui..."
                 :required false
                 :value (:total_outage row)})))

(defn build-incidents-form
  [title row]
  (let [fields (build-incidents-fields row)
        href "/admin/incidents/save"
        buttons (build-modal-buttons)]
    (form href fields buttons)))

(defn build-incidents-modal
  [title row]
  (build-modal title row (build-incidents-form title row)))

(defn incidents-edit-view
  [title row rows]
  (list
   (incidents-view "incidents Manteniento" rows)
   (build-incidents-modal title row)))

(defn incidents-add-view
  [title row rows]
  (list
   (incidents-view "incidents Mantenimiento" rows)
   (build-incidents-modal title row)))

(defn incidents-modal-script
  []
  (modal-script))
