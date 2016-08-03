import { AngularResourceProxy, createThunkAction } from "metabase/lib/redux";

const PermissionsAPI = new AngularResourceProxy("Permissions", ["groups"]);

export const FETCH_PERMISSIONS_GROUPS = "FETCH_PERMISSIONS_GROUPS";


export const fetchGroups = createThunkAction(FETCH_PERMISSIONS_GROUPS, function() {
    return async function(dispatch, getState) {
        let groups = await PermissionsAPI.groups();

        console.log('groups = ', groups);

        return groups;
    };
});
