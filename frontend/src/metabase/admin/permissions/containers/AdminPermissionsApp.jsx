import React, { Component, PropTypes } from "react";
import { connect } from "react-redux";

import AdminPermissions from "../components/AdminPermissions.jsx";

const mapStateToProps = function(state, props) {
    console.log('state ->', state); // NOCOMMIT
    console.log('props ->', props); // NOCOMMIT
    console.log('props.location.query.section ->', props.location.query.section); // NOCOMMIT
    return {
        activeSection: props.location.query.section
    };
}

@connect(mapStateToProps)
export default class AdminPermissionsApp extends Component {
    render() {
        return <AdminPermissions {...this.props} />;
    }
}
