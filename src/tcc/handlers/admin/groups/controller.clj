(ns tcc.handlers.admin.groups.controller
  (:require
   [tcc.handlers.admin.groups.model :refer [get-groups get-groups-id]]
   [tcc.handlers.admin.groups.view :refer [groups-view groups-form-view]]
   [tcc.layout :refer [application error-404]]
   [tcc.models.crud :refer [build-form-delete build-form-save]]
   [tcc.models.util :refer [get-session-id user-level]]
   [hiccup.core :refer [html]]))

(defn groups
  [request]
  (let [title "Groups"
        ok (get-session-id request)
        js nil
        rows (get-groups)
        content (groups-view title rows)]
    (if (= (user-level request) "S")
      (application request title ok js content)
      (application request title ok nil "Not authorized to access this item! (level 'S')"))))

(defn groups-add-form
  [_]
  (let [title "New Groups"
        row nil
        content (groups-form-view title row)]
    (html content)))

(defn groups-edit-form
  [_ id]
  (let [title "Edit Groups"
        row (get-groups-id id)
        content (groups-form-view title row)]
    (html content)))

(defn groups-save
  [{params :params}]
  (let [table "groups"
        result (build-form-save params table)]
    (if result
      {:status 200 :headers {"Content-Type" "application/json"} :body "{\"ok\":true}"}
      {:status 500 :headers {"Content-Type" "application/json"} :body "{\"ok\":false}"})))

(defn groups-delete
  [_ id]
  (let [table "groups"
        result (build-form-delete table id)]
    (if result
      {:status 302 :headers {"Location" "/admin/groups"}}
      (error-404 "Unable to process record!" "/admin/groups"))))
