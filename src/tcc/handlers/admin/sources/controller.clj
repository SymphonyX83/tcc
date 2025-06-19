(ns tcc.handlers.admin.sources.controller
  (:require
   [tcc.handlers.admin.sources.model :refer [get-sources get-sources-id]]
   [tcc.handlers.admin.sources.view :refer [sources-view sources-form-view]]
   [tcc.layout :refer [application error-404]]
   [tcc.models.crud :refer [build-form-delete build-form-save]]
   [tcc.models.util :refer [get-session-id user-level]]
   [hiccup.core :refer [html]]))

(defn sources
  [request]
  (let [title "Sources"
        ok (get-session-id request)
        js nil
        rows (get-sources)
        content (sources-view title rows)]
    (if (= (user-level request) "S")
      (application request title ok js content)
      (application request title ok nil "Not authorized to access this item! (level 'S')"))))

(defn sources-add-form
  [_]
  (let [title "New Sources"
        row nil
        content (sources-form-view title row)]
    (html content)))

(defn sources-edit-form
  [_ id]
  (let [title "Edit Sources"
        row (get-sources-id id)
        content (sources-form-view title row)]
    (html content)))

(defn sources-save
  [{params :params}]
  (let [table "sources"
        result (build-form-save params table)]
    (if result
      {:status 200 :headers {"Content-Type" "application/json"} :body "{\"ok\":true}"}
      {:status 500 :headers {"Content-Type" "application/json"} :body "{\"ok\":false}"})))

(defn sources-delete
  [_ id]
  (let [table "sources"
        result (build-form-delete table id)]
    (if result
      {:status 302 :headers {"Location" "/admin/sources"}}
      (error-404 "Unable to process record!" "/admin/sources"))))
