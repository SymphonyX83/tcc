(ns sk.handlers.admin.incidents.controller
  (:require [sk.layout :refer [application error-404]]
            [sk.models.util :refer [get-session-id user-level]]
            [sk.models.crud :refer [build-form-save build-form-delete]]
            [sk.handlers.admin.incidents.model :refer [get-incidents get-incidents-id]]
            [sk.handlers.admin.incidents.view :refer [incidents-view incidents-edit-view incidents-add-view incidents-modal-script]]))

(defn incidents [_]
  (let [title "Incidents"
        ok (get-session-id)
        js nil
        rows (get-incidents)
        content (incidents-view title rows)]
    (if
     (or
      (= (user-level) "A")
      (= (user-level) "S"))
      (application title ok js content)
      (application title ok nil "Only <strong>los administrators </strong> can access this option!!!"))))

(defn incidents-edit
  [id]
  (let [title "Modificar incidents"
        ok (get-session-id)
        js (incidents-modal-script)
        row (get-incidents-id  id)
        rows (get-incidents)
        content (incidents-edit-view title row rows)]
    (application title ok js content)))

(defn incidents-save
  [{params :params}]
  (let [table "incidents"
        result (build-form-save params table)]
    (if (= result true)
      (error-404 "Record se processo correctamente!" "/admin/incidents")
      (error-404 "No se pudo procesar el record!" "/admin/incidents"))))

(defn incidents-add
  [_]
  (let [title "Crear nuevo incidents"
        ok (get-session-id)
        js (incidents-modal-script)
        row nil
        rows (get-incidents)
        content (incidents-add-view title row rows)]
    (application title ok js content)))

(defn incidents-delete
  [id]
  (let [table "incidents"
        result (build-form-delete table id)]
    (if (= result true)
      (error-404 "Record se processo correctamente!" "/admin/incidents")
      (error-404 "No se pudo procesar el record!" "/admin/incidents"))))
