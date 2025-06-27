(ns tcc.routes.proutes
  (:require
   [compojure.core :refer [defroutes GET POST]]
   [tcc.handlers.incidents.controller :as incidents-dashboard]
   [tcc.handlers.oncall.controller :as oncall-dashboard]
   [tcc.handlers.admin.incidents.controller :as incidents-controller]
   [tcc.handlers.admin.sources.controller :as sources-controller]
   [tcc.handlers.admin.locations.controller :as locations-controller]
   [tcc.handlers.admin.detections.controller :as detections-controller]
   [tcc.handlers.admin.oncall.controller :as oncall-controller]
   [tcc.handlers.admin.groups.controller :as groups-controller]
   [tcc.handlers.admin.employees.controller :as employees-controller]
   [tcc.handlers.admin.users.controller :as users-controller]
   [tcc.handlers.reports.users.controller :as users-report]
   [tcc.handlers.users.controller :as users-dashboard]))

(defroutes proutes
  (GET "/incidents" params [] (incidents-dashboard/incidents params))
  (GET "/oncall" params [] (oncall-dashboard/oncall params))
  (GET "/admin/incidents" params [] (incidents-controller/incidents params))
  (GET "/admin/incidents/add-form" params [] (incidents-controller/incidents-add-form params))
  (GET "/admin/incidents/edit-form/:id" [id :as request] (incidents-controller/incidents-edit-form request id))
  (POST "/admin/incidents/save" params [] (incidents-controller/incidents-save params))
  (GET "/admin/incidents/delete/:id" [id :as request] (incidents-controller/incidents-delete request id))
  (GET "/admin/sources" params [] (sources-controller/sources params))
  (GET "/admin/sources/add-form" params [] (sources-controller/sources-add-form params))
  (GET "/admin/sources/edit-form/:id" [id :as request] (sources-controller/sources-edit-form request id))
  (POST "/admin/sources/save" params [] (sources-controller/sources-save params))
  (GET "/admin/sources/delete/:id" [id :as request] (sources-controller/sources-delete request id))
  (GET "/admin/locations" params [] (locations-controller/locations params))
  (GET "/admin/locations/add-form" params [] (locations-controller/locations-add-form params))
  (GET "/admin/locations/edit-form/:id" [id :as request] (locations-controller/locations-edit-form request id))
  (POST "/admin/locations/save" params [] (locations-controller/locations-save params))
  (GET "/admin/locations/delete/:id" [id :as request] (locations-controller/locations-delete request id))
  (GET "/admin/detections" params [] (detections-controller/detections params))
  (GET "/admin/detections/add-form" params [] (detections-controller/detections-add-form params))
  (GET "/admin/detections/edit-form/:id" [id :as request] (detections-controller/detections-edit-form request id))
  (POST "/admin/detections/save" params [] (detections-controller/detections-save params))
  (GET "/admin/detections/delete/:id" [id :as request] (detections-controller/detections-delete request id))
  (GET "/admin/oncall" params [] (oncall-controller/oncall params))
  (GET "/admin/oncall/add-form" params [] (oncall-controller/oncall-add-form params))
  (GET "/admin/oncall/edit-form/:id" [id :as request] (oncall-controller/oncall-edit-form request id))
  (POST "/admin/oncall/save" params [] (oncall-controller/oncall-save params))
  (GET "/admin/oncall/delete/:id" [id :as request] (oncall-controller/oncall-delete request id))
  (GET "/admin/groups" params [] (groups-controller/groups params))
  (GET "/admin/groups/add-form" params [] (groups-controller/groups-add-form params))
  (GET "/admin/groups/edit-form/:id" [id :as request] (groups-controller/groups-edit-form request id))
  (POST "/admin/groups/save" params [] (groups-controller/groups-save params))
  (GET "/admin/groups/delete/:id" [id :as request] (groups-controller/groups-delete request id))
  (GET "/admin/employees" params [] (employees-controller/employees params))
  (GET "/admin/employees/add-form" params [] (employees-controller/employees-add-form params))
  (GET "/admin/employees/edit-form/:id" [id :as request] (employees-controller/employees-edit-form request id))
  (POST "/admin/employees/save" params [] (employees-controller/employees-save params))
  (GET "/admin/employees/delete/:id" [id :as request] (employees-controller/employees-delete request id))
  (GET "/reports/users" params [] (users-report/users params))
  (GET "/admin/users" params [] (users-controller/users params))
  (GET "/admin/users/add-form" params [] (users-controller/users-add-form params))
  (GET "/admin/users/edit-form/:id" [id :as request] (users-controller/users-edit-form request id))
  (POST "/admin/users/save" params [] (users-controller/users-save params))
  (GET "/admin/users/delete/:id" [id :as request] (users-controller/users-delete request id))
  (GET "/users" params [] (users-dashboard/users params)))