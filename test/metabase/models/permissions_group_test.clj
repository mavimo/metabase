(ns metabase.models.permissions-group-test
  (:require [expectations :refer :all]
            [metabase.db :as db]
            [metabase.models.permissions-group :as perm-group])
  (:import metabase.models.permissions_group.PermissionsGroupInstance))

;; check that we can get the magic permissions groups through the helper functions

(expect PermissionsGroupInstance (perm-group/default))
(expect PermissionsGroupInstance (perm-group/admin))

(expect "Default" (:name (perm-group/default)))
(expect "Admin"   (:name (perm-group/admin)))


;; make sure we're not allowed to delete the magic groups
(expect Exception (db/cascade-delete! 'PermissionsGroup :id (:id (perm-group/default))))
(expect Exception (db/cascade-delete! 'PermissionsGroup :id (:id (perm-group/admin))))
