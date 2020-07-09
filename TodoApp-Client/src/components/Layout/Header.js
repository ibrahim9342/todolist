import React, { Component } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { logout } from "../../actions/userActions";

class Header extends Component {
  render() {

    const { validToken, user } = this.props.security;


if (validToken && user) {
  //console.log("gsakjhfdgsaj");
} else {
  //console.log("gsakjhfdgsaj");
}

    return (
      <nav className="main-header navbar navbar-expand navbar-white navbar-light">
        <ul className="navbar-nav">
          <li className="nav-item">
            <a className="nav-link" data-widget="pushmenu" href="!#" role="button">
              <i className="fas fa-bars"></i>
            </a>
          </li>
        </ul>
      </nav>
    );
  }
}

Header.propTypes = {
  logout: PropTypes.func.isRequired,
  security: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  security: state.security
});

export default connect(
  mapStateToProps,
  { logout }
)(Header);
