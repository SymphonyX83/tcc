(ns tcc.handlers.users.controller
  (:require
   [tcc.handlers.users.model :refer [get-users]]
   [tcc.handlers.users.view :refer [users-view]]
   [tcc.layout :refer [application]]
   [tcc.models.util :refer [get-session-id]]))

(defn users
  [request]
  (let [title "Dashboard"
        ok (get-session-id request)
        js nil
        rows (get-users)
        content (users-view title rows)]
    (application request title ok js content)))
