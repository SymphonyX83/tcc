(ns tcc.handlers.admin.rgroups.controller
  (:require
   [tcc.handlers.admin.rgroups.model :refer [get-rgroups get-rgroups-id]]
   [tcc.handlers.admin.rgroups.view :refer [rgroups-view rgroups-form-view]]
   [tcc.layout :refer [application error-404]]
   [tcc.models.crud :refer [build-form-delete build-form-save]]
   [tcc.models.util :refer [get-session-id user-level]]
   [hiccup.core :refer [html]]))

(defn rgroups
  [request]
  (let [title "Rgroups"
        ok (get-session-id request)
        js nil
        rows (get-rgroups)
        content (rgroups-view title rows)]
    (if (= (user-level request) "S")
      (application request title ok js content)
      (application request title ok nil "Not authorized to access this item! (level 'S')"))))

(defn rgroups-add-form
  [_]
  (let [title "New Rgroups"
        row nil
        content (rgroups-form-view title row)]
    (html content)))

(defn rgroups-edit-form
  [_ id]
  (let [title "Edit Rgroups"
        row (get-rgroups-id id)
        content (rgroups-form-view title row)]
    (html content)))

(defn rgroups-save
  [{params :params}]
  (let [table "rgroups"
        result (build-form-save params table)]
    (if result
      {:status 200 :headers {"Content-Type" "application/json"} :body "{\"ok\":true}"}
      {:status 500 :headers {"Content-Type" "application/json"} :body "{\"ok\":false}"})))

(defn rgroups-delete
  [_ id]
  (let [table "rgroups"
        result (build-form-delete table id)]
    (if result
      {:status 302 :headers {"Location" "/admin/rgroups"}}
      (error-404 "Unable to process record!" "/admin/rgroups"))))
