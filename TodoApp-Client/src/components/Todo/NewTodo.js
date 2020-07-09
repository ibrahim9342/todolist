import React, { Component } from 'react'
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createProject, getTodo } from "../../actions/todoActions";
import classnames from "classnames";

export class NewTodo extends Component {
    constructor() {
        super();
        this.state = {
            id: "",
            todoName: "",
            todoDesc: "",
            completed: false,
            errors: {}
        };
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    //life cycle hooks
    componentWillReceiveProps(nextProps) {
        if (nextProps.errors) {
            this.setState({ errors: nextProps.errors });
        }

        const {
            id,
            todoName,
            todoDesc,
            completed,
        } = nextProps.todo;

        this.setState({
            id,
            todoName,
            todoDesc,
            completed
        });
    }

    componentDidMount() {
        const { id } = this.props.match.params;
        if (id !== undefined) {
            this.props.getTodo(id, this.props.history);
        }
    }

    onChange(e) {
        this.setState({ [e.target.name]: (e.target.type === "checkbox" ? e.target.checked : e.target.value) });
    }

    onSubmit(e) {
        e.preventDefault();
        const newProject = {
            id: this.state.id,
            todoName: this.state.todoName,
            todoDesc: this.state.todoDesc,
            completed: this.state.completed
        };
        this.props.createProject(newProject, this.props.history);
    }

    render() {
        const { errors } = this.state;
        const colCenterBlock = {
            float: "none",
            display: "block",
            margin: "0 auto"
        };

        return (
            <div className="content-wrapper">
                <div className="content-header" />
                <section className="content">
                    <div className="col-sm-6" style={colCenterBlock}>
                        <div className="card card-primary">
                            <div className="card-header">
                                <h3 className="card-title">Create New Todo</h3>
                            </div>
                            <form onSubmit={this.onSubmit}>
                                <div className="card-body">
                                    <div className="form-group">
                                        <label> Todo Name </label>
                                        <input
                                            type="text"
                                            className={classnames("form-control form-control-lg", {
                                                "is-invalid": errors.todoName
                                            })}
                                            placeholder="Todo Name"
                                            name="todoName"
                                            value={this.state.todoName}
                                            onChange={this.onChange} />
                                        {errors.todoName && (
                                            <div className="invalid-feedback">
                                                {errors.todoName}
                                            </div>
                                        )}
                                    </div>
                                    <div className="form-group">
                                        <label> Todo Description </label>
                                        <textarea
                                            className={classnames("form-control form-control-lg", {
                                                "is-invalid": errors.todoDesc
                                            })}
                                            placeholder="Todo Description"
                                            name="todoDesc"
                                            value={this.state.todoDesc}
                                            onChange={this.onChange} />
                                        {errors.todoDesc && (
                                            <div className="invalid-feedback">
                                                {errors.todoDesc}
                                            </div>
                                        )}
                                    </div>
                                    <div className="form-group">
                                        <div className="custom-control custom-switch custom-switch-off-danger custom-switch-on-success">
                                            <input
                                                type="checkbox"
                                                className="custom-control-input"
                                                id="customSwitch3"
                                                name="completed"
                                                checked={this.state.completed}
                                                onChange={this.onChange} />
                                            <label className="custom-control-label" htmlFor="customSwitch3">
                                                Completed
                                            </label>
                                        </div>
                                    </div>
                                    <input type="submit" className="btn btn-primary btn-block mt-4" />
                                </div>
                            </form>
                        </div>
                    </div>
                </section>
            </div>
        )
    }
}


NewTodo.propTypes = {
    getTodo: PropTypes.func.isRequired,
    createProject: PropTypes.func.isRequired,
    todo: PropTypes.object.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    todo: state.todo.todo,
    errors: state.errors
});

export default connect(
    mapStateToProps,
    { getTodo, createProject }
)(NewTodo);

