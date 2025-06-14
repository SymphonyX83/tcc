(ns tcc.handlers.admin.employees.controller
  (:require
   [hiccup.core :refer [html]]
   [tcc.handlers.admin.employees.model :refer [get-employees get-employees-id]]
   [tcc.handlers.admin.employees.view :refer [employees-form-view
                                              employees-view]]
   [tcc.layout :refer [application error-404]]
   [tcc.models.crud :refer [build-form-delete build-form-save]]
   [tcc.models.util :refer [get-session-id user-level]]))

(defn employees
  [request]
  (let [title "Employees"
        ok (get-session-id request)
        js nil
        rows (get-employees)
        content (employees-view title rows)]
    (if (= (user-level request) "S")
      (application request title ok js content)
      (application request title ok nil "Not authorized to access this item! (level 'S')"))))

(defn employees-add-form
  [_]
  (let [title "New Employees"
        row nil
        content (employees-form-view title row)]
    (html content)))

(defn employees-edit-form
  [_ id]
  (let [title "Edit Employees"
        row (get-employees-id id)
        content (employees-form-view title row)]
    (html content)))

(defn employees-save
  [{params :params}]
  (let [table "employees"
        result (build-form-save params table)]
    (if result
      {:status 200 :headers {"Content-Type" "application/json"} :body "{\"ok\":true}"}
      {:status 500 :headers {"Content-Type" "application/json"} :body "{\"ok\":false}"})))

(defn employees-delete
  [_ id]
  (let [table "employees"
        result (build-form-delete table id)]
    (if result
      {:status 302 :headers {"Location" "/admin/employees"}}
      (error-404 "Unable to process record!" "/admin/employees"))))
