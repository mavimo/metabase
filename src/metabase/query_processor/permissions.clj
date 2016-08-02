(ns metabase.query-processor.permissions
  "Logic related to whether a given user has permissions to run/edit a given query."
  (:require [metabase.db :as db]))

(defn- user-has-native-query-write-access? [user-id database-id]
  (boolean (seq (db/query {:select    [:dp.native_query_write_access]
                           :from      [[:core_user :u]]
                           :left-join [[:permissions_group_membership :m]  [:= :u.id        :m.user_id]
                                       [:permissions_group            :g]  [:= :m.group_id  :g.id]
                                       [:database_permissions         :dp] [:= :dp.group_id :g.id]]
                           :where     [:and [:= :u.id                         user-id]
                                            [:= :dp.database_id               database-id]
                                            [:= :dp.native_query_write_access true]]
                           :limit     1}))))

(defn- x []
  ((resolve 'db/honeysql->sql) {:select    [:*]
                                :from      [[(db/entity->table-name 'User) :u]]
                                :left-join [[:permissions_group_membership :m] [:= :u.id :m.user_id]
                                            [:permissions_group :g] [:= :m.group_id :g.id]
                                            [:database_permissions :dp] [:= :dp.group_id :g.id]]
                                :where     [:and
                                            [:= :u.id 1]
                                            [:= :dp.database_id 1]]}))

(defn user-can-run-query-referencing-tables? [user-id database-id table-ids]
  {:pre [(integer? user-id) (integer? database-id) (set? table-ids) (every? integer? table-ids)]})
