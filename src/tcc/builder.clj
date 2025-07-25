(ns tcc.builder
  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [tcc.models.crud :refer [get-table-columns]]
            [tcc.models.routes :as routes]))

;; --- GRID TEMPLATES (existing) ---

(def controller-template
  "(ns tcc.handlers.admin._table_.controller
  (:require
   [tcc.handlers.admin._table_.model :refer [get-_table_ get-_table_-id]]
   [tcc.handlers.admin._table_.view :refer [_table_-view _table_-form-view]]
   [tcc.layout :refer [application error-404]]
   [tcc.models.crud :refer [build-form-delete build-form-save]]
   [tcc.models.util :refer [get-session-id user-level]]
   [hiccup.core :refer [html]]))

(defn _table_
  [request]
  (let [title \"_TableTitle_\"
        ok (get-session-id request)
        js nil
        rows (get-_table_)
        content (_table_-view title rows)]
    (if (= (user-level request) \"S\")
      (application request title ok js content)
      (application request title ok nil \"Not authorized to access this item! (level 'S')\"))))

(defn _table_-add-form
  [_]
  (let [title \"New _TableTitle_\"
        row nil
        content (_table_-form-view title row)]
    (html content)))

(defn _table_-edit-form
  [_ id]
  (let [title \"Edit _TableTitle_\"
        row (get-_table_-id id)
        content (_table_-form-view title row)]
    (html content)))

(defn _table_-save
  [{params :params}]
  (let [table \"_table_\"
        result (build-form-save params table)]
    (if result
      {:status 200 :headers {\"Content-Type\" \"application/json\"} :body \"{\\\"ok\\\":true}\"}
      {:status 500 :headers {\"Content-Type\" \"application/json\"} :body \"{\\\"ok\\\":false}\"})))

(defn _table_-delete
  [_ id]
  (let [table \"_table_\"
        result (build-form-delete table id)]
    (if result
      {:status 302 :headers {\"Location\" \"/admin/_table_\"}}
      (error-404 \"Unable to process record!\" \"/admin/_table_\"))))
")

(def view-template
  "(ns tcc.handlers.admin._table_.view
  (:require [tcc.models.form :refer [form build-field build-modal-buttons]]
            [tcc.models.grid :refer [build-grid]]))

(defn _table_-view
  [title rows]
  (let [labels [_labels_]
        db-fields [_dbfields_]
        fields (apply array-map (interleave db-fields labels))
        table-id \"_table__table\"
        href \"/admin/_table_\"
        args {:new true :edit true :delete true}]
    (build-grid title rows table-id fields href args)))

(defn build-_table_-fields
  [row]
  (list
    (build-field {:id \"id\" :type \"hidden\" :name \"id\" :value (:id row)})
    _fields_
  ))

(defn _table_-form-view
  [title row]
  (form \"/admin/_table_/save\" (build-_table_-fields row) (build-modal-buttons) title {:bare true}))
")

(def model-template
  "(ns tcc.handlers.admin._table_.model
  (:require [tcc.models.crud :refer [Query db]]))

(def get-_table_-sql
  (str \"SELECT * FROM _table_\"))

(defn get-_table_
  []
  (Query db get-_table_-sql))

(defn get-_table_-id
  [id]
  (first (Query db (str get-_table_-sql \" WHERE _table_.id=\" id))))
")

;; --- DASHBOARD TEMPLATES (new) ---

(def controller-dashboard-template
  "(ns tcc.handlers._table_.controller
  (:require
   [tcc.handlers._table_.model :refer [get-_table_]]
   [tcc.handlers._table_.view :refer [_table_-view]]
   [tcc.layout :refer [application]]
   [tcc.models.util :refer [get-session-id]]))

(defn _table_
  [request]
  (let [title \"_TableTitle_\"
        ok (get-session-id request)
        js nil
        rows (get-_table_)
        content (_table_-view title rows)]
    (application request title ok js content)))
")

(def view-dashboard-template
  "(ns tcc.handlers._table_.view
  (:require [tcc.models.grid :refer [build-dashboard]]))

(defn _table_-view
  [title rows]
  (let [labels [_labels_]
        db-fields [_dbfields_]
        fields (apply array-map (interleave db-fields labels))
        table-id \"_table__table\"]
    (build-dashboard title rows table-id fields)))
")

(def model-dashboard-template
  "(ns tcc.handlers._table_.model
  (:require [tcc.models.crud :refer [Query db]]))

(def get-_table_-sql
  (str \"SELECT * FROM _table_\"))

(defn get-_table_
  []
  (Query db get-_table_-sql))
")

;; --- REPORT TEMPLATES (new) ---

(def controller-report-template
  "(ns tcc.handlers.reports._table_.controller
  (:require
   [tcc.handlers.reports._table_.model :refer [get-_table_]]
   [tcc.handlers.reports._table_.view :refer [_table_-view]]
   [tcc.layout :refer [application]]
   [tcc.models.util :refer [get-session-id]]))

(defn _table_
  [params]
  (let [title \"_TableTitle_ Report\"
        ok (get-session-id params)
        js nil
        rows (get-_table_)
        content (_table_-view title rows)]
    (application params title ok js content)))
")

(def view-report-template
  "(ns tcc.handlers.reports._table_.view
  (:require [tcc.models.grid :refer [build-dashboard]]))

(defn _table_-view
  [title rows]
  (let [table-id \"_table__table\"
        labels []
        db-fields []
        fields (apply array-map (interleave db-fields labels))]
    (build-dashboard title rows table-id fields)))
")

(def model-report-template
  "(ns tcc.handlers.reports._table_.model
  (:require [tcc.models.crud :refer [db Query]]))

(def get-_table_-sql
  (str \"SELECT * FROM _table_\"))

(defn get-_table_
  []
  (Query db get-_table_-sql))
")

;; --- TEMPLATE RENDERING ---

(defn render-template [template m]
  (reduce (fn [s [k v]]
            (let [pattern (case k
                            :table "_table_"
                            :TableTitle "_TableTitle_"
                            :labels "_labels_"
                            :dbfields "_dbfields_"
                            :fields "_fields_"
                            (str "\\{\\{" (name k) "\\}\\}"))]
              (str/replace s (re-pattern pattern) v)))
          template
          m))

(defn field-block [fields]
  (apply str
         (for [[label field] fields]
           (str "(build-field {:label \"" label "\" :type \"text\" :id \"" field "\" :name \"" field "\" :placeholder \"" label " here...\" :required false :value (get row :" field ")})\n    "))))

(defn auto-label [field]
  (-> field
      (clojure.string/replace #"_" " ")
      (clojure.string/capitalize)))

;; --- FILE GENERATION ---

(defn generate-files [table fields]
  (let [TableTitle (str/capitalize table)
        labels (str/join " " (map (fn [[label _]] (str "\"" label "\"")) fields))
        dbfields (str/join " " (map (fn [[_ field]] (str ":" field)) fields))
        fields-block (field-block fields)
        m {:table table
           :TableTitle TableTitle
           :labels labels
           :dbfields dbfields
           :fields fields-block}
        base-path (str "src/tcc/handlers/admin/" table "/")]
    (io/make-parents (str base-path "controller.clj"))
    (spit (str base-path "controller.clj") (render-template controller-template m))
    (spit (str base-path "view.clj") (render-template view-template m))
    (spit (str base-path "model.clj") (render-template model-template m))
    (routes/process-grid table)
    (println (str "Code generated in: src/tcc/handlers/admin/" table))))

(defn generate-dashboard-files [table fields]
  (let [TableTitle (str/capitalize table)
        labels (str/join " " (map (fn [[label _]] (str "\"" label "\"")) fields))
        dbfields (str/join " " (map (fn [[_ field]] (str ":" field)) fields))
        m {:table table
           :TableTitle TableTitle
           :labels labels
           :dbfields dbfields}
        base-path (str "src/tcc/handlers/" table "/")]
    (io/make-parents (str base-path "controller.clj"))
    (spit (str base-path "controller.clj") (render-template controller-dashboard-template m))
    (spit (str base-path "view.clj") (render-template view-dashboard-template m))
    (spit (str base-path "model.clj") (render-template model-dashboard-template m))
    (routes/process-dashboard table)
    (println (str "Dashboard code generated in: src/tcc/handlers/" table))))

(defn generate-report-files [table]
  (let [TableTitle (str/capitalize table)
        m {:table table
           :TableTitle TableTitle}
        base-path (str "src/tcc/handlers/reports/" table "/")]
    (io/make-parents (str base-path "controller.clj"))
    (spit (str base-path "controller.clj") (render-template controller-report-template m))
    (spit (str base-path "view.clj") (render-template view-report-template m))
    (spit (str base-path "model.clj") (render-template model-report-template m))
    (routes/process-report table)
    (println (str "Report code generated in: src/tcc/handlers/reports/" table))))

;; --- USAGE ---

(defn usage []
  (println "Usage: lein run -m builder grid <table> <Label1>:<field1> <Label2>:<field2> ...")
  (println "       lein run -m builder dashboard <table> <Label1>:<field1> <Label2>:<field2> ...")
  (println "       lein run -m builder report <table>")
  (println "Example: lein run -m builder grid users Name:name Email:email")
  (println "         lein run -m builder dashboard users Name:name Email:email")
  (println "         lein run -m builder report users"))

;; --- MAIN ENTRYPOINTS ---

(defn build-grid
  [& args]
  (let [[table & field-pairs] args]
    (cond
      (nil? table) (usage)
      (empty? field-pairs)
      ;; If no fields given, auto-generate from DB
      (let [fields (for [field (map name (get-table-columns table))]
                     [(auto-label field) field])]
        (generate-files table (rest fields)))
      :else
      (let [fields (map #(let [[label field] (str/split % #":")]
                           [label field])
                        field-pairs)]
        (generate-files table fields)))))

(defn build-dashboard
  [& args]
  (let [[table & field-pairs] args]
    (cond
      (nil? table) (usage)
      (empty? field-pairs)
      ;; If no fields given, auto-generate from DB
      (let [fields (for [field (map name (get-table-columns table))]
                     [(auto-label field) field])]
        (generate-dashboard-files table (rest fields)))
      :else
      (let [fields (map #(let [[label field] (str/split % #":")]
                           [label field])
                        field-pairs)]
        (generate-dashboard-files table fields)))))

(defn build-report
  [& args]
  (let [[table] args]
    (if (nil? table)
      (usage)
      (generate-report-files table))))

(comment
  (map name (get-table-columns "users"))
  (auto-label "name")
  (let [fields (for [field (map name (get-table-columns "users"))]
                 [(auto-label field) field])]
    (rest fields)))
