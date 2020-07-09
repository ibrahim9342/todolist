import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { login } from "../../actions/userActions";

export class Signin extends Component {

    constructor() {
        super();
        this.state = {
            username: "",
            password: "",
            errors: {}
        };
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }


    componentDidMount() {
        if (this.props.security.validToken) {
            //this.props.history.push("/dashboard");
            //Refresh Page
            //this.props.history.go(0);
            window.location.href = "/dashboard";
        }
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.security.validToken) {
            //this.props.history.push("/dashboard");
            //Refresh Page
            //this.props.history.go(0);
            window.location.href = "/dashboard";
        }

        if (nextProps.errors) {
            this.setState({ errors: nextProps.errors });
        }
    }

    onSubmit(e) {
        e.preventDefault();
        const LoginRequest = {
            username: this.state.username,
            password: this.state.password
        };

        this.props.login(LoginRequest);
    }

    onChange(e) {
        this.setState({ [e.target.name]: e.target.value });
    }

    render() {
        return (
            <div className="login-box">
                <div className="login-logo">
                    <a href="!#"><b>TODO </b>APP</a>
                </div>
                <div className="card">
                    <div className="card-body login-card-body">
                        <p className="login-box-msg">Sign in to start your session</p>

                        <form onSubmit={this.onSubmit}>
                            <div className="input-group mb-3">
                                <input type="username"
                                    className="form-control"
                                    placeholder="Username"
                                    name="username"
                                    value={this.state.username}
                                    onChange={this.onChange}
                                />
                                <div className="input-group-append">
                                    <div className="input-group-text">
                                        <span className="fas fa-envelope"></span>
                                    </div>
                                </div>
                            </div>
                            <div className="input-group mb-3">
                                <input type="password"
                                    className="form-control"
                                    placeholder="Password"
                                    name="password"
                                    value={this.state.password}
                                    onChange={this.onChange}
                                />
                                <div className="input-group-append">
                                    <div className="input-group-text">
                                        <span className="fas fa-lock"></span>
                                    </div>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-8">
                                    <p className="mb-0">
                                        <Link className="text-center" to="/signup">
                                            Register a new membership
                                        </Link>
                                    </p>
                                </div>
                                <div className="col-4">
                                    <input type="submit" className="btn btn-primary btn-block" />
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}

Signin.propTypes = {
    login: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired,
    security: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    security: state.security,
    errors: state.errors
});

export default connect(
    mapStateToProps,
    { login }
)(Signin);

