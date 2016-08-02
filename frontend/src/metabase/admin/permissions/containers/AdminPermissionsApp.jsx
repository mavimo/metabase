import React, { Component, PropTypes } from "react";
import { connect } from "react-redux";

import AdminPermissions from "../components/AdminPermissions.jsx";

const mapStateToProps = function(state, props) {
    console.log('state ->', state);
    console.log('props ->', props);
    return {
        here: true
    };
}

@connect(mapStateToProps)
export default class AdminPermissionsApp extends Component {
    render() {
        return <AdminPermissions {...this.props} />;
    }
}
