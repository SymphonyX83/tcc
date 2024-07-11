(ns sk.handlers.admin.rgroups.controller
  (:require [sk.layout :refer [application error-404]]
            [sk.models.util :refer [get-session-id user-level]]
            [sk.models.crud :refer [build-form-save build-form-delete]]
            [sk.handlers.admin.rgroups.model :refer [get-rgroups get-rgroups-id]]
            [sk.handlers.admin.rgroups.view :refer [rgroups-view rgroups-edit-view rgroups-add-view rgroups-modal-script]]))

(defn rgroups [_]
  (let [title "Rgroups"
        ok (get-session-id)
        js nil
        rows (get-rgroups)
        content (rgroups-view title rows)]
    (if
     (or
      (= (user-level) "A")
      (= (user-level) "S"))
      (application title ok js content)
      (application title ok nil "Only <strong>los administrators </strong> can access this option!!!"))))

(defn rgroups-edit
  [id]
  (let [title "Modificar rgroups"
        ok (get-session-id)
        js (rgroups-modal-script)
        row (get-rgroups-id  id)
        rows (get-rgroups)
        content (rgroups-edit-view title row rows)]
    (application title ok js content)))

(defn rgroups-save
  [{params :params}]
  (let [table "rgroups"
        result (build-form-save params table)]
    (if (= result true)
      (error-404 "Record se processo correctamente!" "/admin/rgroups")
      (error-404 "No se pudo procesar el record!" "/admin/rgroups"))))

(defn rgroups-add
  [_]
  (let [title "Crear nuevo rgroups"
        ok (get-session-id)
        js (rgroups-modal-script)
        row nil
        rows (get-rgroups)
        content (rgroups-add-view title row rows)]
    (application title ok js content)))

(defn rgroups-delete
  [id]
  (let [table "rgroups"
        result (build-form-delete table id)]
    (if (= result true)
      (error-404 "Record se processo correctamente!" "/admin/rgroups")
      (error-404 "No se pudo procesar el record!" "/admin/rgroups"))))
