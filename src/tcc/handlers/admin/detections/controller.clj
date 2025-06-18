(ns tcc.handlers.admin.detections.controller
  (:require
   [tcc.handlers.admin.detections.model :refer [get-detections get-detections-id]]
   [tcc.handlers.admin.detections.view :refer [detections-view detections-form-view]]
   [tcc.layout :refer [application error-404]]
   [tcc.models.crud :refer [build-form-delete build-form-save]]
   [tcc.models.util :refer [get-session-id user-level]]
   [hiccup.core :refer [html]]))

(defn detections
  [request]
  (let [title "Detections"
        ok (get-session-id request)
        js nil
        rows (get-detections)
        content (detections-view title rows)]
    (if (= (user-level request) "S")
      (application request title ok js content)
      (application request title ok nil "Not authorized to access this item! (level 'S')"))))

(defn detections-add-form
  [_]
  (let [title "New Detections"
        row nil
        content (detections-form-view title row)]
    (html content)))

(defn detections-edit-form
  [_ id]
  (let [title "Edit Detections"
        row (get-detections-id id)
        content (detections-form-view title row)]
    (html content)))

(defn detections-save
  [{params :params}]
  (let [table "detections"
        result (build-form-save params table)]
    (if result
      {:status 200 :headers {"Content-Type" "application/json"} :body "{\"ok\":true}"}
      {:status 500 :headers {"Content-Type" "application/json"} :body "{\"ok\":false}"})))

(defn detections-delete
  [_ id]
  (let [table "detections"
        result (build-form-delete table id)]
    (if result
      {:status 302 :headers {"Location" "/admin/detections"}}
      (error-404 "Unable to process record!" "/admin/detections"))))
