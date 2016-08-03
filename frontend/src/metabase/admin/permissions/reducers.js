import { handleActions } from 'redux-actions';

import {
    FETCH_PERMISSIONS_GROUPS
} from './actions';


export const groups = handleActions({
    [FETCH_PERMISSIONS_GROUPS]: {
        next: (state, {payload}) => payload
    }
}, null);
