(ns sk.handlers.admin.sources.view
  (:require [ring.util.anti-forgery :refer [anti-forgery-field]]
            [sk.models.form :refer [form build-hidden-field build-field build-select build-radio build-modal-buttons build-textarea]]
            [sk.models.grid :refer [build-grid build-modal modal-script]]))

(defn sources-view
  [title rows]
  (let [labels ["NAME" "DECRIPTION"]
        db-fields [:name :decription]
        fields (zipmap db-fields labels)
        table-id "sources_table"
        args {:new true :edit true :delete true}
        href "/admin/sources"]
    (build-grid title rows table-id fields href args)))

(defn build-sources-fields
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
   (build-textarea {:label "DECRIPTION"
                    :id "decription"
                    :name "decription"
                    :rows "3"
                    :placeholder "decription aqui..."
                    :required false
                    :value (:decription row)})))

(defn build-sources-form
  [title row]
  (let [fields (build-sources-fields row)
        href "/admin/sources/save"
        buttons (build-modal-buttons)]
    (form href fields buttons)))

(defn build-sources-modal
  [title row]
  (build-modal title row (build-sources-form title row)))

(defn sources-edit-view
  [title row rows]
  (list
   (sources-view "sources Manteniento" rows)
   (build-sources-modal title row)))

(defn sources-add-view
  [title row rows]
  (list
   (sources-view "sources Mantenimiento" rows)
   (build-sources-modal title row)))

(defn sources-modal-script
  []
  (modal-script))
