(ns sk.handlers.admin.sources.controller
  (:require [sk.layout :refer [application error-404]]
            [sk.models.util :refer [get-session-id user-level]]
            [sk.models.crud :refer [build-form-save build-form-delete]]
            [sk.handlers.admin.sources.model :refer [get-sources get-sources-id]]
            [sk.handlers.admin.sources.view :refer [sources-view sources-edit-view sources-add-view sources-modal-script]]))

(defn sources [_]
  (let [title "Sources"
        ok (get-session-id)
        js nil
        rows (get-sources)
        content (sources-view title rows)]
    (if
     (or
      (= (user-level) "A")
      (= (user-level) "S"))
      (application title ok js content)
      (application title ok nil "Only <strong>los administrators </strong> can access this option!!!"))))

(defn sources-edit
  [id]
  (let [title "Modificar sources"
        ok (get-session-id)
        js (sources-modal-script)
        row (get-sources-id  id)
        rows (get-sources)
        content (sources-edit-view title row rows)]
    (application title ok js content)))

(defn sources-save
  [{params :params}]
  (let [table "sources"
        result (build-form-save params table)]
    (if (= result true)
      (error-404 "Record se processo correctamente!" "/admin/sources")
      (error-404 "No se pudo procesar el record!" "/admin/sources"))))

(defn sources-add
  [_]
  (let [title "Crear nuevo sources"
        ok (get-session-id)
        js (sources-modal-script)
        row nil
        rows (get-sources)
        content (sources-add-view title row rows)]
    (application title ok js content)))

(defn sources-delete
  [id]
  (let [table "sources"
        result (build-form-delete table id)]
    (if (= result true)
      (error-404 "Record se processo correctamente!" "/admin/sources")
      (error-404 "No se pudo procesar el record!" "/admin/sources"))))
