(ns tcc.routes.proutes
  (:require
   [compojure.core :refer [defroutes GET POST]]
   [tcc.handlers.admin.rgroups.controller :as rgroups-controller]
   [tcc.handlers.admin.locations.controller :as locations-controller]
   [tcc.handlers.admin.employees.controller :as employees-controller]
   [tcc.handlers.admin.users.controller :as users-controller]
   [tcc.handlers.reports.users.controller :as users-report]
   [tcc.handlers.users.controller :as users-dashboard]))

(defroutes proutes
  (GET "/admin/rgroups" params [] (rgroups-controller/rgroups params))
  (GET "/admin/rgroups/add-form" params [] (rgroups-controller/rgroups-add-form params))
  (GET "/admin/rgroups/edit-form/:id" [id :as request] (rgroups-controller/rgroups-edit-form request id))
  (POST "/admin/rgroups/save" params [] (rgroups-controller/rgroups-save params))
  (GET "/admin/rgroups/delete/:id" [id :as request] (rgroups-controller/rgroups-delete request id))
  (GET "/admin/locations" params [] (locations-controller/locations params))
  (GET "/admin/locations/add-form" params [] (locations-controller/locations-add-form params))
  (GET "/admin/locations/edit-form/:id" [id :as request] (locations-controller/locations-edit-form request id))
  (POST "/admin/locations/save" params [] (locations-controller/locations-save params))
  (GET "/admin/locations/delete/:id" [id :as request] (locations-controller/locations-delete request id))
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