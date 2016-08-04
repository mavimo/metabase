import { createAction } from "redux-actions";
import { handleActions } from 'redux-actions';

import { CLOSE_QB_NEWB_MODAL } from "metabase/query_builder/actions";

export const setUser = createAction("SET_USER");


export const currentUser = handleActions({
    ["SET_USER"]: { next: (state, { payload }) => payload },
    [CLOSE_QB_NEWB_MODAL]: { next: (state, { payload }) => ({...state, is_qbnewb: false}) }
}, null);
