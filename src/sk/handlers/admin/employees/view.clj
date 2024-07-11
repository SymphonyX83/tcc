(ns sk.handlers.admin.employees.view
  (:require [ring.util.anti-forgery :refer [anti-forgery-field]]
            [sk.models.form :refer [form build-hidden-field build-field build-select build-radio build-modal-buttons build-textarea]]
            [sk.handlers.admin.employees.model :refer [rgroup-options location-options]]
            [sk.models.grid :refer [build-grid build-modal modal-script]]))

(defn employees-view
  [title rows]
  (let [labels ["NAME" "FIRSTNAME" "LASTNAME" "PHONE(1)" "PHONE(2)" "GROUP" "LOCATION"]
        db-fields [:name :firstname :lastname :pphone :sphone :rgroup_id_formatted :location_id_formatted]
        fields (zipmap db-fields labels)
        table-id "employees_table"
        args {:new true :edit true :delete true}
        href "/admin/employees"]
    (build-grid title rows table-id fields href args)))

(defn build-employees-fields
  [row]
  (list
   (build-hidden-field {:id "id"
                        :name "id"
                        :value (:id row)})
   (build-field {:label "NAME"
                 :type "text"
                 :id "name"
                 :name "name"
                 :placeholder "name aqui..."
                 :required false
                 :value (:name row)})
   (build-field {:label "FIRSTNAME"
                 :type "text"
                 :id "firstname"
                 :name "firstname"
                 :placeholder "firstname aqui..."
                 :required false
                 :value (:firstname row)})
   (build-field {:label "LASTNAME"
                 :type "text"
                 :id "lastname"
                 :name "lastname"
                 :placeholder "lastname aqui..."
                 :required false
                 :value (:lastname row)})
   (build-field {:label "PPHONE"
                 :type "text"
                 :id "pphone"
                 :name "pphone"
                 :placeholder "pphone aqui..."
                 :required false
                 :value (:pphone row)})
   (build-field {:label "SPHONE"
                 :type "text"
                 :id "sphone"
                 :name "sphone"
                 :placeholder "sphone aqui..."
                 :required false
                 :value (:sphone row)})
   (build-select {:label "IS_MANAGER?"
                  :id "is_manager"
                  :name "is_manager"
                  :value (:is_manager row)
                  :required true
                  :error "This field is required..."
                  :options [{:value ""
                             :label "Select an answer..."}
                            {:value "Y"
                             :label "Yes"}
                            {:value "N"
                             :label "No"}]})
   (build-select {:label "RESOLUTION GROUP"
                  :id "rgroup_id"
                  :name "rgroup_id"
                  :required false
                  :error "This is a required field..."
                  :value (:rgroup_id row)
                  :options (rgroup-options)})
   (build-select {:label "LOCATION"
                  :id "location_id"
                  :name "location_id"
                  :required false
                  :error "This is a required field..."
                  :value (:location_id row)
                  :options (location-options)})))

(defn build-employees-form
  [title row]
  (let [fields (build-employees-fields row)
        href "/admin/employees/save"
        buttons (build-modal-buttons)]
    (form href fields buttons)))

(defn build-employees-modal
  [title row]
  (build-modal title row (build-employees-form title row)))

(defn employees-edit-view
  [title row rows]
  (list
   (employees-view "employees Manteniento" rows)
   (build-employees-modal title row)))

(defn employees-add-view
  [title row rows]
  (list
   (employees-view "employees Mantenimiento" rows)
   (build-employees-modal title row)))

(defn employees-modal-script
  []
  (modal-script))
