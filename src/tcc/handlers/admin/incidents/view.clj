(ns tcc.handlers.admin.incidents.view
  (:require [tcc.models.form :refer [form build-field build-modal-buttons]]
            [tcc.models.grid :refer [build-grid]]))

(defn incidents-view
  [title rows]
  (let [labels ["Status" "Tier" "Sev" "Number" "Start" "End" "Location" "Summary" "Resolution Group" "Coordinators"]
        db-fields [:status :ci_tier :severity :number :start_time :end_time :location_id :summary :rgroup_id :coordinators]
        fields (apply array-map (interleave db-fields labels))
        table-id "incidents_table"
        href "/admin/incidents"
        args {:new true :edit true :delete true}]
    (build-grid title rows table-id fields href args)))

(defn build-incidents-fields
  [row]
  (list
   (build-field {:id "id" :type "hidden" :name "id" :value (:id row)})
   (build-field {:label "Status" :type "radio" :name "status" :required true :value (get row :status) :options [{:id "statusO" :value "1" :label "Open"} {:id "statusC" :value "0" :label "Closed"}]})
   (build-field {:label "Number" :type "text" :id "number" :name "number" :placeholder "Number here..." :required false :value (get row :number)})
   (build-field {:label "Environment" :type "text" :id "environment" :name "environment" :placeholder "Environment here..." :required false :value (get row :environment)})
   (build-field {:label "Location" :type "text" :id "location_id" :name "location_id" :placeholder "Location id here..." :required false :value (get row :location_id)})
   (build-field {:label "Tier" :type "text" :id "ci_tier" :name "ci_tier" :placeholder "Ci tier here..." :required false :value (get row :ci_tier)})
   (build-field {:label "Severity" :type "text" :id "severity" :name "severity" :placeholder "Severity here..." :required false :value (get row :severity)})
   (build-field {:label "Business impact" :type "text" :id "business_impact" :name "business_impact" :placeholder "Describe the Business impact..." :required false :value (get row :business_impact)})
   (build-field {:label "Detection" :type "text" :id "detection_id" :name "detection_id" :placeholder "Detection id here..." :required false :value (get row :detection_id)})
   (build-field {:label "Summary" :type "text" :id "summary" :name "summary" :placeholder "Summary here..." :required false :value (get row :summary)})
   (build-field {:label "Resolution Group" :type "text" :id "rgroup_id" :name "rgroup_id" :placeholder "Rgroup id here..." :required false :value (get row :rgroup_id)})
   (build-field {:label "Bridge Details" :type "text" :id "bridge" :name "bridge" :placeholder "Bridge here..." :required false :value (get row :bridge)})
   (build-field {:label "Current status" :type "text" :id "current_status" :name "current_status" :placeholder "Current status here..." :required false :value (get row :current_status)})
   (build-field {:label "Source of incident" :type "text" :id "source_id" :name "source_id" :placeholder "Source id here..." :required false :value (get row :source_id)})
   (build-field {:label "Start time" :type "text" :id "start_time" :name "start_time" :placeholder "Start time here..." :required false :value (get row :start_time)})
   (build-field {:label "End time" :type "text" :id "end_time" :name "end_time" :placeholder "End time here..." :required false :value (get row :end_time)})
   (build-field {:label "Coordinator1 id" :type "text" :id "coordinator1_id" :name "coordinator1_id" :placeholder "Coordinator1 id here..." :required false :value (get row :coordinator1_id)})
   (build-field {:label "Coordinator2 id" :type "text" :id "coordinator2_id" :name "coordinator2_id" :placeholder "Coordinator2 id here..." :required false :value (get row :coordinator2_id)})
   (build-field {:label "Coordinator3 id" :type "text" :id "coordinator3_id" :name "coordinator3_id" :placeholder "Coordinator3 id here..." :required false :value (get row :coordinator3_id)})))

(defn incidents-form-view
  [title row]
  (form "/admin/incidents/save" (build-incidents-fields row) (build-modal-buttons) title {:bare true}))
