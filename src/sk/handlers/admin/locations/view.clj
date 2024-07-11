(ns sk.handlers.admin.locations.view
  (:require [ring.util.anti-forgery :refer [anti-forgery-field]]
            [sk.models.form :refer [form build-hidden-field build-field build-select build-radio build-modal-buttons build-textarea]]
            [sk.models.grid :refer [build-grid build-modal modal-script]]))

(defn locations-view
  [title rows]
  (let [labels ["NAME" "CITY" "COUNTRY" "EUS CONTACT" "DESCRIPTION"]
        db-fields [:name :city :country :eus_contact :description]
        fields (zipmap db-fields labels)
        table-id "locations_table"
        args {:new true :edit true :delete true}
        href "/admin/locations"]
    (build-grid title rows table-id fields href args)))

(defn build-locations-fields
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
   (build-field {:label "CITY"
                 :type "text"
                 :id "city"
                 :name "city"
                 :placeholder "city aqui..."
                 :required false
                 :value (:city row)})
   (build-field {:label "COUNTRY"
                 :type "text"
                 :id "country"
                 :name "country"
                 :placeholder "country aqui..."
                 :required false
                 :value (:country row)})
   (build-field {:label "EUS_CONTACT"
                 :type "text"
                 :id "eus_contact"
                 :name "eus_contact"
                 :placeholder "eus_contact aqui..."
                 :required false
                 :value (:eus_contact row)})
   (build-textarea {:label "DESCRIPTION"
                    :id "description"
                    :name "description"
                    :rows "3"
                    :placeholder "description aqui..."
                    :required false
                    :value (:description row)})))

(defn build-locations-form
  [title row]
  (let [fields (build-locations-fields row)
        href "/admin/locations/save"
        buttons (build-modal-buttons)]
    (form href fields buttons)))

(defn build-locations-modal
  [title row]
  (build-modal title row (build-locations-form title row)))

(defn locations-edit-view
  [title row rows]
  (list
   (locations-view "locations Manteniento" rows)
   (build-locations-modal title row)))

(defn locations-add-view
  [title row rows]
  (list
   (locations-view "locations Mantenimiento" rows)
   (build-locations-modal title row)))

(defn locations-modal-script
  []
  (modal-script))
