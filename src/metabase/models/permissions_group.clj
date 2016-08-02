(ns metabase.models.permissions-group
  (:require [metabase.db :as db]
            [metabase.models.interface :as i]))

(i/defentity PermissionsGroup :permissions_group)

(defn default
  "Fetch the `Default` permissions group, creating it if needed."
  ^metabase.models.permissions_group.PermissionsGroupInstance []
  (or (db/select-one PermissionsGroup
        :name "Default")
      (db/insert! PermissionsGroup
        :name "Default")))

(defn admin
  "Fetch the `Admin` permissions group, creating it if needed."
  ^metabase.models.permissions_group.PermissionsGroupInstance []
  (or (db/select-one PermissionsGroup
        :name "Admin")
      (db/insert! PermissionsGroup
        :name "Admin")))
