import React, { Component } from 'react'
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createItem, getItem, getAllTodo } from "../../actions/itemActions";
import classnames from "classnames";

export class NewItem extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: "",
            itemName: "",
            itemDesc: "",
            completed: false,
            errors: {},
            selectedTodo: 0,
            tododata: []
        };
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    //life cycle hooks
    componentWillReceiveProps(nextProps) {
        if (nextProps.errors) {
            this.setState({ errors: nextProps.errors });
        }

        if (this.state.id !== "") {
            const {
                id,
                itemName,
                itemDesc,
                completed
            } = nextProps.item;

            this.setState({
                id,
                itemName,
                itemDesc,
                completed,
                selectedTodo: nextProps.todo[0].id
            });
        }else{
            this.setState({
                selectedTodo: nextProps.todo[0].id
            })
        }
        
        this.setState({
            tododata: nextProps.todo
        });



    }

    componentDidMount() {

        this.props.getAllTodo(); 
        debugger;
        const { id } = this.props.match.params;
        if (id !== undefined) {
            this.setState({ id: id });
            this.props.getItem(id, this.props.history);
        }
    }

    onChange(e) {
        this.setState({ [e.target.name]: (e.target.type === "checkbox" ? e.target.checked : e.target.value) });
    }

    onSubmit(e) {
        e.preventDefault();
        const newItem = {
            id: this.state.id,
            itemName: this.state.itemName,
            itemDesc: this.state.itemDesc,
            completed: this.state.completed,
        };

        this.props.createItem(this.state.selectedTodo, newItem, this.props.history);
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
                                <h3 className="card-title">Create New Item</h3>
                            </div>
                            <form onSubmit={this.onSubmit}>
                                <div className="card-body">
                                    <div className="form-group">
                                        <label> Item Name </label>
                                        <input
                                            type="text"
                                            className={classnames("form-control form-control-lg", {
                                                "is-invalid": errors.itemName
                                            })}
                                            placeholder="Item Name"
                                            name="itemName"
                                            value={this.state.itemName}
                                            onChange={this.onChange} />
                                        {errors.itemName && (
                                            <div className="invalid-feedback">
                                                {errors.itemName}
                                            </div>
                                        )}
                                    </div>
                                    <div className="form-group">
                                        <label> Item Description </label>
                                        <textarea
                                            className={classnames("form-control form-control-lg", {
                                                "is-invalid": errors.itemDesc
                                            })}
                                            placeholder="Item Description"
                                            name="itemDesc"
                                            value={this.state.itemDesc}
                                            onChange={this.onChange} />
                                        {errors.itemDesc && (
                                            <div className="invalid-feedback">
                                                {errors.itemDesc}
                                            </div>
                                        )}
                                    </div>
                                    <div className="form-group">
                                        <label> Todo </label>
                                        <select className="custom-select"
                                            name="selectedTodo"
                                            value={this.state.selectedTodo}
                                            onChange={this.onChange}>
                                            {this.state.tododata.map(todo => (
                                                <option key={todo.id}
                                                    value={todo.id}
                                                >{todo.todoName}</option>
                                            ))
                                            }
                                        </select>
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


NewItem.propTypes = {
    getItem: PropTypes.func.isRequired,
    createItem: PropTypes.func.isRequired,
    item: PropTypes.object.isRequired,
    getAllTodo: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
    item: state.item.item,
    todo: state.item.todos,
    errors: state.errors
});

export default connect(
    mapStateToProps,
    { getItem, createItem, getAllTodo }
)(NewItem);

