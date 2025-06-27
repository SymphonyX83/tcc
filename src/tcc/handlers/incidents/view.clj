(ns tcc.handlers.incidents.view
  (:require [tcc.models.grid :refer [build-dashboard]]))

(defn incidents-view
  [title rows]
  (let [labels ["location" "source" "group" "status" "start time" "end time" "coordinator1" "coordinator2" "coordinator3"]
        db-fields [:location_name :source_name :group_name :status_formatted
                   :start_time_formatted :end_time_formatted
                   :coordinator1_name :coordinator2_name :coordinator3_name]
        fields (apply array-map (interleave db-fields labels))
        table-id "incidents_table"]
    (build-dashboard title rows table-id fields)))
