(ns sk.handlers.admin.oncall.controller
  (:require [sk.layout :refer [application error-404]]
            [sk.models.util :refer [get-session-id user-level]]
            [sk.models.crud :refer [build-form-save build-form-delete]]
            [sk.handlers.admin.oncall.model :refer [get-oncall get-oncall-id]]
            [sk.handlers.admin.oncall.view :refer [oncall-view oncall-edit-view oncall-add-view oncall-modal-script]]))

(defn oncall [_]
  (let [title "Oncall"
        ok (get-session-id)
        js nil
        rows (get-oncall)
        content (oncall-view title rows)]
    (if
     (or
      (= (user-level) "A")
      (= (user-level) "S"))
      (application title ok js content)
      (application title ok nil "Only <strong>los administrators </strong> can access this option!!!"))))

(defn oncall-edit
  [id]
  (let [title "Modificar oncall"
        ok (get-session-id)
        js (oncall-modal-script)
        row (get-oncall-id  id)
        rows (get-oncall)
        content (oncall-edit-view title row rows)]
    (application title ok js content)))

(defn oncall-save
  [{params :params}]
  (let [table "oncall"
        result (build-form-save params table)]
    (if (= result true)
      (error-404 "Record se processo correctamente!" "/admin/oncall")
      (error-404 "No se pudo procesar el record!" "/admin/oncall"))))

(defn oncall-add
  [_]
  (let [title "Crear nuevo oncall"
        ok (get-session-id)
        js (oncall-modal-script)
        row nil
        rows (get-oncall)
        content (oncall-add-view title row rows)]
    (application title ok js content)))

(defn oncall-delete
  [id]
  (let [table "oncall"
        result (build-form-delete table id)]
    (if (= result true)
      (error-404 "Record se processo correctamente!" "/admin/oncall")
      (error-404 "No se pudo procesar el record!" "/admin/oncall"))))
