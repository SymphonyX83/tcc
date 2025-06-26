(ns tcc.menu)

(def reports-items
  [["/reports/users" "Users"]])

(def admin-items
  [["/admin/users" "Users" "S"]
   ["/admin/employees" "Employees" "S"]
   ["/admin/locations" "Locations" "S"]
   ["/admin/rgroups" "Resolution Groups" "S"]
   ["/admin/detections" "Detection Methods" "S"]
   ["/admin/sources" "Source of Incidents" "S"]
   ["/admin/oncall" "Oncall Schedule" "S"]]) ; Only system users

(def menu-config
  {:nav-links [["/" "Home"]
               ["/admin/incidents" "Incidents"]
               ["/users" "Users"]]
   :dropdowns {:reports {:id "navdrop0"
                         :data-id "reports"
                         :label "Reports"
                         :items reports-items}
               :admin {:id "navdrop1"
                       :data-id "admin"
                       :label "Administration"
                       :items admin-items}}})
