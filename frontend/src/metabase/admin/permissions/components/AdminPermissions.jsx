import React, { Component, PropTypes } from "react";

import cx from 'classnames';

export default class AdminPermissions extends Component {

    constructor(props, context) {
        super(props, context);

        this.state = {
            activeSection: "Groups"
        };
    }

    /* componentDidMount() {
     *     this.setState({
     *         activeSection: "Groups"
     *     });
     * }*/

    render() {
        const renderLeftNavItem = (function(name, href) {
            return (
                <li key={name}>
                    <a href={href}
                       className={cx("AdminList-item flex align-center justify-between no-decoration", {selected: this.state && this.state.activeSection === name})}>
                        {name}
                    </a>
                </li>
            );
        }).bind(this);

        return (
            <div className="MetadataEditor full-height flex flex-column flex-full p4">
                <div className="MetadataEditor-header clearfix relative flex-no-shrink">
                    <div className="MetadataEditor-headerSection float-left h2 text-grey-4">
                        Permissions
                    </div>
                </div>
                <div className="MetadataEditor-main flex flex-row flex-full mt2">
                    <div className="MetadataEditor-table-list AdminList flex-no-shrink">
                        <ul className="AdminList-items pt1">
                            {renderLeftNavItem("Groups", "/admin/permissions/groups")}
                            {renderLeftNavItem("Data",   "/admin/permissions/data")}
                        </ul>
                    </div>
                </div>
            </div>
        );
    }
}
