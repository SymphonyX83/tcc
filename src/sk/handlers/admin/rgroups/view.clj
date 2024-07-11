(ns sk.handlers.admin.rgroups.view
  (:require [ring.util.anti-forgery :refer [anti-forgery-field]]
            [sk.models.form :refer [form build-hidden-field build-field build-select build-radio build-modal-buttons build-textarea]]
            [sk.handlers.admin.rgroups.model :refer [parent-options manager-options]]
            [sk.models.grid :refer [build-grid build-modal modal-script]]))

(defn rgroups-view
  [title rows]
  (let [labels ["NAME" "PARENT" "ROTATIONAL PHONE" "MANAGER" "DESCRIPTION"]
        db-fields [:name :parent_formatted :rotational_phone :manager_id_formatted :description]
        fields (zipmap db-fields labels)
        table-id "rgroups_table"
        args {:new true :edit true :delete true}
        href "/admin/rgroups"]
    (build-grid title rows table-id fields href args)))

(defn build-rgroups-fields
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
                 :required true
                 :value (:name row)})
   (build-select {:label "PARENT"
                  :id "parent"
                  :name "parent"
                  :required false
                  :error "This field is required..."
                  :value (:parent row)
                  :options (parent-options)})
   (build-field {:label "ROTATIONAL_PHONE"
                 :type "text"
                 :id "rotational_phone"
                 :name "rotational_phone"
                 :placeholder "rotational_phone aqui..."
                 :required false
                 :value (:rotational_phone row)})
   (build-select {:label "MANAGER"
                  :id "manager_id"
                  :name "manager_id"
                  :required false
                  :error "This field is required..."
                  :value (:manager_id row)
                  :options (manager-options)})
   (build-textarea {:label "DESCRIPTION"
                    :id "description"
                    :name "description"
                    :rows "3"
                    :placeholder "description aqui..."
                    :required false
                    :value (:description row)})))

(defn build-rgroups-form
  [title row]
  (let [fields (build-rgroups-fields row)
        href "/admin/rgroups/save"
        buttons (build-modal-buttons)]
    (form href fields buttons)))

(defn build-rgroups-modal
  [title row]
  (build-modal title row (build-rgroups-form title row)))

(defn rgroups-edit-view
  [title row rows]
  (list
   (rgroups-view "rgroups Manteniento" rows)
   (build-rgroups-modal title row)))

(defn rgroups-add-view
  [title row rows]
  (list
   (rgroups-view "rgroups Mantenimiento" rows)
   (build-rgroups-modal title row)))

(defn rgroups-modal-script
  []
  (modal-script))
