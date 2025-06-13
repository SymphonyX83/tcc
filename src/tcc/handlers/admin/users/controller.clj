(ns tcc.handlers.admin.users.controller
  (:require
   [tcc.handlers.admin.users.model :refer [get-user get-users]]
   [tcc.handlers.admin.users.view :refer [users-view]]
   [tcc.layout :refer [application error-404]]
   [tcc.models.crud :refer [build-form-delete build-form-save]]
   [tcc.models.util :refer [get-session-id user-level]]
   [hiccup.core :refer [html]]))

(defn users
  [request]
  (let [title "Users"
        ok (get-session-id request)
        js nil
        rows (get-users)
        content (users-view title rows)]
    (if (= (user-level request) "S")
      (application request title ok js content)
      (application request title ok nil "Not authorized to access this item! (level 'S')"))))

(defn users-add-form
  [_]
  (let [title "New User"
        row nil]
    (html (tcc.handlers.admin.users.view/users-add-form title row))))

(defn users-edit-form
  [_ id]
  (let [title "Edit User"
        row (get-user id)]
    (html (tcc.handlers.admin.users.view/users-edit-form title row))))

(defn users-save
  [{params :params}]
  (let [table "users"
        result (build-form-save params table)]
    (if result
      {:status 200 :headers {"Content-Type" "application/json"} :body "{\"ok\":true}"}
      {:status 500 :headers {"Content-Type" "application/json"} :body "{\"ok\":false}"})))

(defn users-delete
  [_ id]
  (let [table "users"
        result (build-form-delete table id)]
    (if result
      {:status 302 :headers {"Location" "/admin/users"}}
      (error-404 "Unable to process record!" "/admin/users"))))
