(ns tcc.handlers.admin.incidents.view
  (:require
   [tcc.handlers.admin.incidents.model :refer [sources-options]]
   [tcc.models.form :refer [build-field build-modal-buttons form]]
   [tcc.models.grid :refer [build-grid]]
   [tcc.models.util :refer [get-options]]))

(defn incidents-view
  [title rows]
  (let [labels ["location" "source" "group" "status" "start time" "end time" "coordinator1" "coordinator2" "coordinator3"]
        db-fields [:location_name :source_name :group_name :status_formatted
                   :start_time_formatted :end_time_formatted
                   :coordinator1_name :coordinator2_name :coordinator3_name]
        fields (apply array-map (interleave db-fields labels))
        table-id "incidents_table"
        href "/admin/incidents"
        args {:new true :edit true :delete true}]
    (build-grid title rows table-id fields href args)))

(defn build-incidents-fields
  [row]
  (list
   (build-field {:id "id" :type "hidden" :name "id" :value (:id row)})
   (build-field {:label "Status" :type "select" :id "status" :name "status" :placeholder "Status here..." :required true :value (get row :status)
                 :options [{:value "1" :label "Open"}
                           {:value "2" :label "Closed"}]})
   (build-field {:label "Number" :type "number" :id "number" :name "number" :placeholder "Number here..." :required true :value (get row :number)
                 :step "1" :min "0"})
   (build-field {:label "Environment" :type "select" :id "environment" :name "environment" :placeholder "Environment here..." :required true :value (get row :environment)
                 :options [{:value "DEV" :label "DEV"}
                           {:value "QA" :label "QA"}
                           {:value "UAT" :label "UAT"}
                           {:value "PROD" :label "PROD"}]})
   (build-field {:label "Location" :type "select" :id "location_id" :name "location_id" :placeholder "Location id here..." :required true :value (get row :location_id)
                 :options (get-options "locations" "id" "name")})
   (build-field {:label "Ci tier" :type "select" :id "ci_tier" :name "ci_tier" :placeholder "Ci tier here..." :required false :value (get row :ci_tier)
                 :options [{:value "1" :label "Tier 1"}
                           {:value "2" :label "Tier 2"}
                           {:value "3" :label "Tier 3"}]})
   (build-field {:label "Severity" :type "select" :id "severity" :name "severity" :placeholder "Severity here..." :required true :value (get row :severity)
                 :options [{:value "1" :label "Severity 1"}
                           {:value "2" :label "Severity 2"}
                           {:value "3" :label "Severity 3"}
                           {:value "4" :label "Serverity 4 "}]})
   (build-field {:label "Business impact" :type "text" :id "business_impact" :name "business_impact" :placeholder "Business impact here..." :required false :value (get row :business_impact)})
   (build-field {:label "Summary" :type "textarea" :rows "4" :id "summary" :name "summary" :placeholder "Summary here..." :required false :value (get row :summary)})
   (build-field {:label "Group" :type "select" :id "group_id" :name "group_id" :placeholder "Group id here..." :required true :value (get row :group_id)
                 :options (get-options "groups" "id" "name" :filter-field "active" :filter-value "Yes")})
   (build-field {:label "Bridge" :type "text" :id "bridge" :name "bridge" :placeholder "Bridge here..." :required false :value (get row :bridge)})
   (build-field {:label "Current status" :type "textarea" :rows "4" :id "current_status" :name "current_status" :placeholder "Current status here..." :required false :value (get row :current_status)})
   (build-field {:label "Source" :type "select" :id "source_id" :name "source_id" :placeholder "Source id here..." :required true :value (get row :source_id)
                 :options (sources-options)})
   (build-field {:label "Start time" :type "datetime-local" :id "start_time" :name "start_time" :placeholder "Start time here..." :required true :value (get row :start_time)})
   (build-field {:label "End time" :type "datetime-local" :id "end_time" :name "end_time" :placeholder "End time here..." :required true :value (get row :end_time)})
   (build-field {:label "Coordinator1" :type "select" :id "coordinator1_id" :name "coordinator1_id" :placeholder "Coordinator1 id here..." :required false :value (get row :coordinator1_id)
                 :options (get-options "employees" "id" "name")})
   (build-field {:label "Coordinator2" :type "select" :id "coordinator2_id" :name "coordinator2_id" :placeholder "Coordinator2 id here..." :required false :value (get row :coordinator2_id)
                 :options (get-options "employees" "id" "name")})
   (build-field {:label "Coordinator3" :type "select" :id "coordinator3_id" :name "coordinator3_id" :placeholder "Coordinator3 id here..." :required false :value (get row :coordinator3_id)
                 :options (get-options "employees" "id" "name")})))

(defn incidents-form-view
  [title row]
  (form "/admin/incidents/save" (build-incidents-fields row) (build-modal-buttons) title {:bare true}))
