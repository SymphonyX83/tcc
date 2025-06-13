(ns tcc.handlers.reports.users.controller
  (:require
   [tcc.handlers.reports.users.model :refer [get-users]]
   [tcc.handlers.reports.users.view :refer [users-view]]
   [tcc.layout :refer [application]]
   [tcc.models.util :refer [get-session-id]]))

(defn users [params]
  (let [title "Users Report"
        ok (get-session-id params)
        js nil
        rows (get-users)
        content (users-view title rows)]
    (application params title ok js content)))

