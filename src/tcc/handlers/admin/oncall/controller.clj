(ns tcc.handlers.admin.oncall.controller
  (:require
   [tcc.handlers.admin.oncall.model :refer [get-oncall get-oncall-id]]
   [tcc.handlers.admin.oncall.view :refer [oncall-view oncall-form-view]]
   [tcc.layout :refer [application error-404]]
   [tcc.models.crud :refer [build-form-delete build-form-save]]
   [tcc.models.util :refer [get-session-id user-level]]
   [hiccup.core :refer [html]]))

(defn oncall
  [request]
  (let [title "Oncall"
        ok (get-session-id request)
        js nil
        rows (get-oncall)
        content (oncall-view title rows)]
    (if (= (user-level request) "S")
      (application request title ok js content)
      (application request title ok nil "Not authorized to access this item! (level 'S')"))))

(defn oncall-add-form
  [_]
  (let [title "New Oncall"
        row nil
        content (oncall-form-view title row)]
    (html content)))

(defn oncall-edit-form
  [_ id]
  (let [title "Edit Oncall"
        row (get-oncall-id id)
        content (oncall-form-view title row)]
    (html content)))

(defn oncall-save
  [{params :params}]
  (let [table "oncall"
        result (build-form-save params table)]
    (if result
      {:status 200 :headers {"Content-Type" "application/json"} :body "{\"ok\":true}"}
      {:status 500 :headers {"Content-Type" "application/json"} :body "{\"ok\":false}"})))

(defn oncall-delete
  [_ id]
  (let [table "oncall"
        result (build-form-delete table id)]
    (if result
      {:status 302 :headers {"Location" "/admin/oncall"}}
      (error-404 "Unable to process record!" "/admin/oncall"))))
