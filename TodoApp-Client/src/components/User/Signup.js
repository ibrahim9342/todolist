import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createNewUser } from "../../actions/userActions";

export class Signup extends Component {


    constructor() {
        super();

        this.state = {
            username: "",
            email: "",
            password: "",
            errors: {}
        };
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    componentDidMount() {
        if (this.props.security.validToken) {
            this.props.history.push("/dashboard");
        }
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.errors) {
            this.setState({ errors: nextProps.errors });
        }
    }

    onSubmit(e) {
        e.preventDefault();
        const newUser = {
            username: this.state.username,
            email: this.state.email,
            password: this.state.password
        };
        this.props.createNewUser(newUser, this.props.history);
    }

    onChange(e) {
        this.setState({ [e.target.name]: e.target.value });
    }



    render() {
        return (
            <div className="register-box">
                <div className="register-logo">
                    <a href="!#"><b>TODO</b> APP</a>
                </div>

                <div className="card">
                    <div className="card-body register-card-body">
                        <p className="login-box-msg">Register a new membership</p>

                        <form onSubmit={this.onSubmit}>
                            <div className="input-group mb-3">
                                <input type="text"
                                    className="form-control"
                                    placeholder="Username"
                                    name="username"
                                    value={this.state.username}
                                    onChange={this.onChange} />
                                <div className="input-group-append">
                                    <div className="input-group-text">
                                        <span className="fas fa-user"></span>
                                    </div>
                                </div>
                            </div>
                            <div className="input-group mb-3">
                                <input type="email"
                                    className="form-control"
                                    placeholder="Email"
                                    name="email"
                                    value={this.state.email}
                                    onChange={this.onChange} />
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
                                    onChange={this.onChange} />
                                <div className="input-group-append">
                                    <div className="input-group-text">
                                        <span className="fas fa-lock"></span>
                                    </div>
                                </div>
                            </div>
                            <div className="row">
                                <div className="col-8">
                                    <Link className="text-center" to="/signin">
                                        Signin
                                    </Link>
                                </div>
                                <div className="col-4">
                                    <button type="submit" className="btn btn-primary btn-block">Register</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}

Signup.propTypes = {
    createNewUser: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired,
    security: PropTypes.object.isRequired
  };
  
  const mapStateToProps = state => ({
    errors: state.errors,
    security: state.security
  });
  export default connect(
    mapStateToProps,
    { createNewUser }
  )(Signup);
  