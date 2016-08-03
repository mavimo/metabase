import React, { Component, PropTypes } from "react";
import { connect } from "react-redux";

import AdminPermissions from "../components/AdminPermissions.jsx";
import { adminPermissionsSelectors } from "../selectors";

const mapStateToProps = function(state, props) {
    return {
        ...adminPermissionsSelectors(state),
        activeSection: props.location.query.section
    };
}

@connect(mapStateToProps)
export default class AdminPermissionsApp extends Component {
    render() {
        return <AdminPermissions {...this.props} />;
    }
}
