import React, { Component } from 'react'
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { logout } from "../../actions/userActions";

export class Sidebar extends Component {

    logout() {
        this.props.logout();
        this.props.history.go(0);
        //window.location.href = "/";
    }

    render() {
        const { user } = this.props.security;
        let currentRole = ""
        if (user.roles) {
            user.roles.map((role) =>
                currentRole = role)
        }
        return (
            <aside className="main-sidebar sidebar-dark-primary elevation-4" >
                <div className="brand-link">
                    <img src="logo192.png" alt="AdminLTE Logo" className="brand-image img-circle elevation-3"
                        style={{ opacity: ".8" }} />
                    <span className="brand-text font-weight-light">Todo App</span>
                </div>
                <div className="sidebar">
                    <div className="user-panel mt-3 pb-3 mb-3 d-flex">
                        <div className="info">
                            <a href="!#" className="d-block">{user.username}</a>
                        </div>
                    </div>
                    <nav className="mt-2">
                        <ul className="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                            <li className="nav-item">
                                <Link className="nav-link" to="/dashboard">
                                    <i className="nav-icon fa fa-chevron-right"></i>
                                    <p>
                                        Dashboard
                                    </p>
                                </Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/todos">
                                    <i className="nav-icon fa fa-chevron-right"></i>
                                    <p>
                                        Todo List
                                    </p>
                                </Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/items">
                                    <i className="nav-icon fa fa-chevron-right"></i>
                                    <p>
                                        Todo Items
                                    </p>
                                </Link>
                            </li>

                            {(currentRole === "ROLE_ADMIN") ?
                                <li className="nav-item">
                                    <Link className="nav-link" to="/alltodos">
                                        <i className="nav-icon fa fa-key"></i>
                                        <p>
                                            All Todo List
                                    </p>
                                    </Link>
                                </li>
                                : null
                            }

                            {(currentRole === "ROLE_ADMIN") ?
                                <li className="nav-item">
                                    <Link className="nav-link" to="/allitems">
                                        <i className="nav-icon fa fa-key"></i>
                                        <p>
                                            All Todo Items
                                    </p>
                                    </Link>
                                </li>
                                : null
                            }

                            <li className="nav-item">
                                <Link className="nav-link" to="/logout"
                                    onClick={this.logout.bind(this)}>
                                    <i className="nav-icon fa fa-chevron-right"></i>
                                    <p>
                                        Logout
                                    </p>
                                </Link>
                            </li>
                        </ul>
                    </nav>
                </div>
            </aside>
        )
    }
}

Sidebar.propTypes = {
    logout: PropTypes.func.isRequired,
    security: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    security: state.security
});

export default connect(
    mapStateToProps,
    { logout }
)(Sidebar);

