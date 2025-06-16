(ns tcc.handlers.admin.locations.controller
  (:require
   [tcc.handlers.admin.locations.model :refer [get-locations get-locations-id]]
   [tcc.handlers.admin.locations.view :refer [locations-view locations-form-view]]
   [tcc.layout :refer [application error-404]]
   [tcc.models.crud :refer [build-form-delete build-form-save]]
   [tcc.models.util :refer [get-session-id user-level]]
   [hiccup.core :refer [html]]))

(defn locations
  [request]
  (let [title "Locations"
        ok (get-session-id request)
        js nil
        rows (get-locations)
        content (locations-view title rows)]
    (if (= (user-level request) "S")
      (application request title ok js content)
      (application request title ok nil "Not authorized to access this item! (level 'S')"))))

(defn locations-add-form
  [_]
  (let [title "New Locations"
        row nil
        content (locations-form-view title row)]
    (html content)))

(defn locations-edit-form
  [_ id]
  (let [title "Edit Locations"
        row (get-locations-id id)
        content (locations-form-view title row)]
    (html content)))

(defn locations-save
  [{params :params}]
  (let [table "locations"
        result (build-form-save params table)]
    (if result
      {:status 200 :headers {"Content-Type" "application/json"} :body "{\"ok\":true}"}
      {:status 500 :headers {"Content-Type" "application/json"} :body "{\"ok\":false}"})))

(defn locations-delete
  [_ id]
  (let [table "locations"
        result (build-form-delete table id)]
    (if result
      {:status 302 :headers {"Location" "/admin/locations"}}
      (error-404 "Unable to process record!" "/admin/locations"))))
