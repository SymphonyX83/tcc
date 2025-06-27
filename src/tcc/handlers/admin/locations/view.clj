(ns tcc.handlers.admin.locations.view
  (:require [tcc.models.form :refer [form build-field build-modal-buttons]]
            [tcc.models.grid :refer [build-grid]]))

(defn locations-view
  [title rows]
  (let [labels ["Name" "City" "State" "Country" "Address" "Zip code"]
        db-fields [:name :city :state :country :address :zip_code]
        fields (apply array-map (interleave db-fields labels))
        table-id "locations_table"
        href "/admin/locations"
        args {:new true :edit true :delete true}]
    (build-grid title rows table-id fields href args)))

(defn build-locations-fields
  [row]
  (list
   (build-field {:id "id" :type "hidden" :name "id" :value (:id row)})
   (build-field {:label "Name" :type "text" :id "name" :name "name" :placeholder "Name here..." :required true :value (get row :name)})
   (build-field {:label "City" :type "text" :id "city" :name "city" :placeholder "City here..." :required false :value (get row :city)})
   (build-field {:label "State" :type "text" :id "state" :name "state" :placeholder "State here..." :required false :value (get row :state)})
   (build-field {:label "Country" :type "text" :id "country" :name "country" :placeholder "Country here..." :required false :value (get row :country)})
   (build-field {:label "Address" :type "text" :id "address" :name "address" :placeholder "Address here..." :required false :value (get row :address)})
   (build-field {:label "Zip code" :type "text" :id "zip_code" :name "zip_code" :placeholder "Zip code here..." :required false :value (get row :zip_code)})))

(defn locations-form-view
  [title row]
  (form "/admin/locations/save" (build-locations-fields row) (build-modal-buttons) title {:bare true}))
