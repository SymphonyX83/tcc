(ns sk.handlers.admin.employees.controller
  (:require [sk.layout :refer [application error-404]]
            [sk.models.util :refer [get-session-id user-level]]
            [sk.models.crud :refer [build-form-save build-form-delete]]
            [sk.handlers.admin.employees.model :refer [get-employees get-employees-id]]
            [sk.handlers.admin.employees.view :refer [employees-view employees-edit-view employees-add-view employees-modal-script]]))

(defn employees [_]
  (let [title "Employees"
        ok (get-session-id)
        js nil
        rows (get-employees)
        content (employees-view title rows)]
    (if
     (or
      (= (user-level) "A")
      (= (user-level) "S"))
      (application title ok js content)
      (application title ok nil "Only <strong>administrators </strong> can access this option!!!"))))

(defn employees-edit
  [id]
  (let [title "Modificar employees"
        ok (get-session-id)
        js (employees-modal-script)
        row (get-employees-id  id)
        rows (get-employees)
        content (employees-edit-view title row rows)]
    (application title ok js content)))

(defn employees-save
  [{params :params}]
  (let [table "employees"
        result (build-form-save params table)]
    (if (= result true)
      (error-404 "Record se processo correctamente!" "/admin/employees")
      (error-404 "No se pudo procesar el record!" "/admin/employees"))))

(defn employees-add
  [_]
  (let [title "Crear nuevo employees"
        ok (get-session-id)
        js (employees-modal-script)
        row nil
        rows (get-employees)
        content (employees-add-view title row rows)]
    (application title ok js content)))

(defn employees-delete
  [id]
  (let [table "employees"
        result (build-form-delete table id)]
    (if (= result true)
      (error-404 "Record se processo correctamente!" "/admin/employees")
      (error-404 "No se pudo procesar el record!" "/admin/employees"))))
