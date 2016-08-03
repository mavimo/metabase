import { createSelector } from 'reselect';

export const adminPermissionsSelectors = createSelector(
    [state => state.permissions.groups],
    (groups) => ({groups})
);

console.log('adminPermissionsSelectors', adminPermissionsSelectors); // NOCOMMIT
