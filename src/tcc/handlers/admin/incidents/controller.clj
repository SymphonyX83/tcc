(ns tcc.handlers.admin.incidents.controller
  (:require
   [tcc.handlers.admin.incidents.model :refer [get-incidents get-incidents-id]]
   [tcc.handlers.admin.incidents.view :refer [incidents-view incidents-form-view]]
   [tcc.layout :refer [application error-404]]
   [tcc.models.crud :refer [build-form-delete build-form-save]]
   [tcc.models.util :refer [get-session-id user-level]]
   [hiccup.core :refer [html]]))

(defn incidents
  [request]
  (let [title "Incidents"
        ok (get-session-id request)
        js nil
        rows (get-incidents)
        content (incidents-view title rows)]
    (if (= (user-level request) "S")
      (application request title ok js content)
      (application request title ok nil "Not authorized to access this item! (level 'S')"))))

(defn incidents-add-form
  [_]
  (let [title "New Incidents"
        row nil
        content (incidents-form-view title row)]
    (html content)))

(defn incidents-edit-form
  [_ id]
  (let [title "Edit Incidents"
        row (get-incidents-id id)
        content (incidents-form-view title row)]
    (html content)))

(defn incidents-save
  [{params :params}]
  (let [table "incidents"
        result (build-form-save params table)]
    (if result
      {:status 200 :headers {"Content-Type" "application/json"} :body "{\"ok\":true}"}
      {:status 500 :headers {"Content-Type" "application/json"} :body "{\"ok\":false}"})))

(defn incidents-delete
  [_ id]
  (let [table "incidents"
        result (build-form-delete table id)]
    (if result
      {:status 302 :headers {"Location" "/admin/incidents"}}
      (error-404 "Unable to process record!" "/admin/incidents"))))
