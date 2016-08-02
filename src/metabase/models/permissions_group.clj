(ns metabase.models.permissions-group
  (:require [metabase.db :as db]
            [metabase.models.interface :as i]
            [metabase.util :as u]))

(i/defentity PermissionsGroup :permissions_group)

(defn- pre-cascade-delete [{group-name :name, id :id}]
  {:pre [(string? group-name)]}
  ;; make sure we're not trying to delete one of the magic groups
  (when (= group-name "Default")
    (throw (Exception. "You cannot delete the 'Default' permissions group!")))
  (when (= group-name "Admin")
    (throw (Exception. "You cannot delete the 'Admin' permissions group!")))
  ;; ok, we're clear; now delete related objects
  (db/cascade-delete! 'DatabasePermissions        :group_id id)
  (db/cascade-delete! 'TablePermissions           :group_id id)
  (db/cascade-delete! 'SchemaPermissions          :group_id id)
  (db/cascade-delete! 'PermissionsGroupMembership :group_id id))

(defn- pre-update [group]
  ;; TODO - make sure we're not trying to update one of the magic groups
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
