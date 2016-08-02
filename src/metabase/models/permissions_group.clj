(ns metabase.models.permissions-group
  (:require [metabase.db :as db]
            [metabase.models.interface :as i]
            [metabase.util :as u]))

(i/defentity PermissionsGroup :permissions_group)

(defn- throw-exception-when-editing-magic-group
  "Make sure we're not trying to edit/delete one of the magic groups, or throw an exception."
  [{group-name :name}]
  {:pre [(string? group-name)]}
  (when (= group-name "Default")
    (throw (Exception. "You cannot delete the 'Default' permissions group!")))
  (when (= group-name "Admin")
    (throw (Exception. "You cannot delete the 'Admin' permissions group!"))))

(defn- pre-cascade-delete [{id :id, :as group}]
  (throw-exception-when-editing-magic-group )
  (db/cascade-delete! 'DatabasePermissions        :group_id id)
  (db/cascade-delete! 'TablePermissions           :group_id id)
  (db/cascade-delete! 'SchemaPermissions          :group_id id)
  (db/cascade-delete! 'PermissionsGroupMembership :group_id id))

(defn- pre-update [{id :id, :as group}]
  (throw-exception-when-editing-magic-group group)
  group)

(u/strict-extend (class PermissionsGroup)
  i/IEntity (merge i/IEntityDefaults
                   {:pre-cascade-delete pre-cascade-delete
                    :pre-update         pre-update}))


;;; magic permissions groups getter helper fns

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
