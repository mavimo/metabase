(ns metabase.api.permissions
  "/api/permissions endpoints."
  (:require [compojure.core :refer [GET]]
            [metabase.api.common :refer :all]
            [metabase.db :as db]
            (metabase.models [permissions-group :refer [PermissionsGroup]])))

(defendpoint GET "/groups"
  "Fetch all `PermissionsGroups`."
  []
  (check-superuser)
  (db/select PermissionsGroup {:order-by [:name]}))


(define-routes)
