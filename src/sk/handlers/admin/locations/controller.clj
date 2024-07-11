(ns sk.handlers.admin.locations.controller
  (:require [sk.layout :refer [application error-404]]
            [sk.models.util :refer [get-session-id user-level]]
            [sk.models.crud :refer [build-form-save build-form-delete]]
            [sk.handlers.admin.locations.model :refer [get-locations get-locations-id]]
            [sk.handlers.admin.locations.view :refer [locations-view locations-edit-view locations-add-view locations-modal-script]]))

(defn locations[_]
  (let [title "Locations"
        ok (get-session-id)
        js nil
        rows (get-locations)
        content (locations-view title rows)]
    (if
      (or
        (= (user-level) "A")
        (= (user-level) "S"))
      (application title ok js content)
      (application title ok nil "Only <strong>los administrators </strong> can access this option!!!"))))

(defn locations-edit
  [id]
  (let [title "Modificar locations"
        ok (get-session-id)
        js (locations-modal-script)
        row (get-locations-id  id)
        rows (get-locations)
        content (locations-edit-view title row rows)]
    (application title ok js content)))

(defn locations-save
  [{params :params}]
  (let [table "locations"
        result (build-form-save params table)]
    (if (= result true)
      (error-404 "Record se processo correctamente!" "/admin/locations")
      (error-404 "No se pudo procesar el record!" "/admin/locations"))))

(defn locations-add
  [_]
  (let [title "Crear nuevo locations"
        ok (get-session-id)
        js (locations-modal-script)
        row nil
        rows (get-locations)
        content (locations-add-view title row rows)]
    (application title ok js content)))

(defn locations-delete
  [id]
  (let [table "locations"
        result (build-form-delete table id)]
    (if (= result true)
      (error-404 "Record se processo correctamente!" "/admin/locations")
      (error-404 "No se pudo procesar el record!" "/admin/locations"))))

