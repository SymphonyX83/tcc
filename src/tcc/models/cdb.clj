(ns tcc.models.cdb
  (:require
   [buddy.hashers :as hashers]
   [tcc.models.crud :refer [db Insert-multi Query!]]))

(def users-rows
  [{:lastname  "User"
    :firstname "Regular"
    :username  "user@example.com"
    :password  (hashers/derive "user")
    :dob       "1957-02-07"
    :email     "user@example.com"
    :level     "U"
    :active    "T"}
   {:lastname "User"
    :firstname "Admin"
    :username "admin@example.com"
    :password (hashers/derive "admin")
    :dob "1957-02-07"
    :email "admin@example.com"
    :level "A"
    :active "T"}
   {:lastname "User"
    :firstname "System"
    :username "system@example.com"
    :password (hashers/derive "system")
    :dob "1957-02-07"
    :email "system@example.com"
    :level "S"
    :active "T"}])

(def employees-rows
  [{:id 1
    :name "Pedro Pacas Verdes"
    :email "ppacas@example.com"
    :pphone "686-123-4334"
    :sphone "686-222-4321"}
   {:id 2
    :name "Maria Pacas Verdes"
    :email "mpacas@example.com"
    :pphone "686-333-5656"
    :sphone "686-221-8987"}
   {:id 3
    :name "Perro Aguayo Campeon"
    :email "paguayo@example.com"
    :pphone "686-666-3354"
    :sphone "686-555-0233"}])

(def groups-rows
  [{:id 1
    :name "Active Directory"
    :email "ad@example.com"
    :description "Active Directory Group"
    :active "Yes"}
   {:id 2
    :name "MYSQL DBA"
    :email "mysql@example.com"
    :description "MySQL DBA Group"
    :active "Yes"}
   {:id 3
    :name "ERP NonSap"
    :email "erp@example.com"
    :description "ERP NonSap Group"
    :active "Yes"}
   {:id 4
    :name "Windows Admins"
    :email "wa@example.com"
    :description "Wintel"
    :active "Yes"}
   {:id 5
    :name "TCCW"
    :email "tccw@example.com"
    :description "TCCW Group"
    :active "Yes"}
   {:id 6
    :name "Citrix"
    :email "citrixadmins@example.com"
    :description "Citrix Admins"
    :active "Yes"}
   {:id 7
    :name "SAP Basis"
    :email "sapb@example.com"
    :description "SAP Basis Admins"
    :active "Yes"}
   {:id 8
    :name "SAP Security"
    :email "sapsec@example.com"
    :description "SAP Security Admins"
    :active "Yes"}
   {:id 9
    :name "Oracle PDBA"
    :email "opdba@example.com"
    :description "Oracle PDBA Admins"
    :active "Yes"}
   {:id 10
    :name "SQL DBA"
    :email "sqldba@example.com"
    :description "SQL DBA Admins"
    :active "Yes"}
   {:id 11
    :name "Unix"
    :email "unixteam@example.com"
    :description "Unix Admins"
    :active "Yes"}
   {:id 12
    :name "Virtualization"
    :email "virtual@example.com"
    :description "Virtualization Admins"
    :active "Yes"}
   {:id 13
    :name "Storage"
    :email "storage@example.com"
    :description "Storage Admins"
    :active "Yes"}])

(def detections-rows
  [{:id 1
    :name "Call/Chat"
    :description "Call or Chat with the user"}
   {:id 2
    :name "Monitoring Tool"
    :description "Monitoring Tool Alert"}
   {:id 5
    :name "Email"
    :description "Email Alert"}
   {:id 7
    :name "Incident"
    :description "Incident Report"}
   {:id 8
    :name "Walk-in"
    :description "Walk-in User"}
   {:id 9
    :name "Escalation"
    :description "Escalation from another team"}])

(def locations-rows
  [{:id 1
    :name "Planta Norte"
    :city "Mexicali"
    :state "BC"
    :country "Mexico"}
   {:id 2
    :name "BT West"
    :city "Mexicali"
    :state "BC"
    :country "Mexico"}
   {:id 3
    :name "RDC3"
    :city "Savannah"
    :state "GA"
    :country "United States"}
   {:id 4
    :name "Planta Sur"
    :city "Mexicali"
    :state "BC"
    :country "Mexico"}
   {:id 5
    :name "Cuyamaca"
    :city "Mexicali"
    :state "BC"
    :country "Mexico"}
   {:id 6
    :name "Dallas Warehouse"
    :city "Dallas"
    :state "Texas"
    :country "United States"}])

(def sources-rows
  [{:id 1
    :detection_id 1
    :name "Service Desk"
    :description "Escalations from Service Desk Through Shared Chatgroup"}
   {:id 3
    :detection_id 2
    :name "OpManager"
    :description "Manage Engine Tool"}
   {:id 4
    :detection_id 2
    :name "OEM"
    :description "Oracle Enterprise Manager"}
   {:id 6
    :detection_id 5
    :name "Monitoring Tool"
    :description "Monitoring Tool Alert"}
   {:id 7
    :detection_id 5
    :name "SME"
    :description "Subject Matter Expert Email"}
   {:id 9
    :detection_id 2
    :name "Xymon"
    :description "Xymon Monitoring Tool"}
   {:id 10
    :detection_id 2
    :name "AppManager"
    :description "Manage Engine Application Manager"}
   {:id 11
    :detection_id 7
    :name "ITSM"
    :description "ServiceNow Platform"}
   {:id 12
    :detection_id 9
    :name "BT Service Desk"
    :description "BT Service Desk Escalation"}
   {:id 13
    :detection_id 9
    :name "Business Stakeholders"
    :description "Business Stakeholders Escalation"}
   {:id 14
    :detection_id 1
    :name "SME"
    :description "Subject Matter Expert Call"}
   {:id 15
    :detection_id 5
    :name "Monitoring Tool"
    :description "Monitoring Tool Alert"}
   {:id 16
    :detection_id 5
    :name "ISP"
    :description "Internet Service Provider Alert"}
   {:id 18
    :detection_id 1
    :name "Previous/Existing Chatgroup"
    :description "Re-incidence"}])

(defn populate-tables
  "Populates table with default data"
  [table rows]
  (Query! db (str "LOCK TABLES " table " WRITE;"))
  (Insert-multi db (keyword table) rows)
  (Query! db "UNLOCK TABLES;"))

(defn database []
  (populate-tables "users" users-rows)
  (populate-tables "employees" employees-rows)
  (populate-tables "groups" groups-rows)
  (populate-tables "detections" detections-rows)
  (populate-tables "locations" locations-rows)
  (populate-tables "sources" sources-rows))
