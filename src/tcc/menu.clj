(ns tcc.menu)

(def reports-items
  [["/reports/users" "Users"]])

(def admin-items
  [["/admin/employees" "Employees"]
   ["/admin/groups" "Groups"]
   ["/admin/oncall" "On Call"]
   ["/admin/locations" "Locations"]
   ["/admin/detections" "Detections"]
   ["/admin/sources" "Sources"]
   ["/admin/incidents" "Incidents"]
   ["/admin/users" "Users" "S"]]) ; Only system users

(def menu-config
  {:nav-links [["/" "Home"]
               ["/oncall" "ON Call"]
               ["/incidents" "Incidents"]]
   :dropdowns {:reports {:id "navdrop0"
                         :data-id "reports"
                         :label "Reports"
                         :items reports-items}
               :admin {:id "navdrop1"
                       :data-id "admin"
                       :label "Administration"
                       :items admin-items}}})
