(ns tcc.handlers.users.model
  (:require
   [tcc.models.crud :refer [db Query]]))

(defn get-users
  []
  (Query db "select * from users_view"))

(comment
  (get-users))
