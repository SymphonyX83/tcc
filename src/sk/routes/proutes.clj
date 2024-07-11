(ns sk.routes.proutes
  (:require [compojure.core :refer [defroutes GET POST]]
            [sk.handlers.admin.users.controller :as users-controller]
            [sk.handlers.admin.locations.controller :as locations-controller]
            [sk.handlers.admin.sources.controller :as sources-controller]
            [sk.handlers.admin.rgroups.controller :as rgroups-controller]
            [sk.handlers.admin.employees.controller :as employees-controller]
            [sk.handlers.admin.incidents.controller :as incidents-controller]
            [sk.handlers.users.controller :as users-dashboard]))

(defroutes proutes
  (GET "/admin/users" params users-controller/users)
  (GET "/admin/users/edit/:id" [id] (users-controller/users-edit id))
  (POST "/admin/users/save" params [] (users-controller/users-save params))
  (GET "/admin/users/add" params [] (users-controller/users-add params))
  (GET "/admin/users/delete/:id" [id] (users-controller/users-delete id))

  (GET "/admin/locations" params locations-controller/locations)
  (GET "/admin/locations/edit/:id" [id] (locations-controller/locations-edit id))
  (POST "/admin/locations/save" params [] (locations-controller/locations-save params))
  (GET "/admin/locations/add" params [] (locations-controller/locations-add params))
  (GET "/admin/locations/delete/:id" [id] (locations-controller/locations-delete id))

  (GET "/admin/sources" params sources-controller/sources)
  (GET "/admin/sources/edit/:id" [id] (sources-controller/sources-edit id))
  (POST "/admin/sources/save" params [] (sources-controller/sources-save params))
  (GET "/admin/sources/add" params [] (sources-controller/sources-add params))
  (GET "/admin/sources/delete/:id" [id] (sources-controller/sources-delete id))

  (GET "/admin/rgroups" params rgroups-controller/rgroups)
  (GET "/admin/rgroups/edit/:id" [id] (rgroups-controller/rgroups-edit id))
  (POST "/admin/rgroups/save" params [] (rgroups-controller/rgroups-save params))
  (GET "/admin/rgroups/add" params [] (rgroups-controller/rgroups-add params))
  (GET "/admin/rgroups/delete/:id" [id] (rgroups-controller/rgroups-delete id))

  (GET "/admin/employees" params employees-controller/employees)
  (GET "/admin/employees/edit/:id" [id] (employees-controller/employees-edit id))
  (POST "/admin/employees/save" params [] (employees-controller/employees-save params))
  (GET "/admin/employees/add" params [] (employees-controller/employees-add params))
  (GET "/admin/employees/delete/:id" [id] (employees-controller/employees-delete id))

  (GET "/admin/incidents" params incidents-controller/incidents)
  (GET "/admin/incidents/edit/:id" [id] (incidents-controller/incidents-edit id))
  (POST "/admin/incidents/save" params [] (incidents-controller/incidents-save params))
  (GET "/admin/incidents/add" params [] (incidents-controller/incidents-add params))
  (GET "/admin/incidents/delete/:id" [id] (incidents-controller/incidents-delete id))

  (GET "/users" params [] (users-dashboard/users params)))
