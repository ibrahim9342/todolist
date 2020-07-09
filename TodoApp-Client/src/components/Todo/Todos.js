import React, { Component } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { deleteTodo, getUserTodos } from "../../actions/todoActions";

import 'react-bootstrap-table-next/dist/react-bootstrap-table2.css';
import 'react-bootstrap-table2-paginator/dist/react-bootstrap-table2-paginator.min.css';

import BootstrapTable from 'react-bootstrap-table-next';
import paginationFactory from 'react-bootstrap-table2-paginator';


class Todos extends Component {
  //constructor to handle errors
  constructor(props) {
    super(props);
    this.state = {
      page: 0,
      sizePerPage: 10,
      errors: {},
      todo: []
    };
    //{id: 1, todoName: "qw dq", todoDesc: "qwdqwdqwdqwdqwd"}
    this.options = {
      page: 1,
      paginationSize: 4,
      pageStartIndex: 1,
      totalSize: this.props.todo.todos.total,
      onPageChange: this.onPageChange,
      onSizePerPageChange: this.onSizePerPageChange
    };

  }

  onDeleteClick = id => {
    this.props.deleteTodo(id);
    this.options.page = 1;
    //this.pageChange(1, 10);
  };

  componentWillReceiveProps(nextProps) {

    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }

    const { todos } = nextProps.todo;
    if (todos.length !== 0) {
      this.options.totalSize = todos.total;
      this.setState({ todo: todos.content });
    }

  }


  componentDidMount() {
    this.props.getUserTodos(this.state.page, this.state.sizePerPage);

    const { todos } = this.props.todo;
    if (todos.length !== 0) {
      this.options.totalSize = todos.total;
      this.setState({ todo: todos.content });
    }
  }

  onPageChange = (page, sizePerPage) => {
    this.pageChange(page, sizePerPage);
  }

  onSizePerPageChange = (sizePerPage, page) => {
    this.pageChange(page, sizePerPage);
  }

  pageChange = (page, sizePerPage) => {
    //alert(`page: ${page}, sizePerPage: ${sizePerPage}, option: ${this.options.pageStartIndex} `);
    this.options.page = page;
    page = page - 1;
    this.props.getUserTodos(page, sizePerPage);
  }

  render() {
    //const { todos } = this.props.todo;
    const columns = [{
      dataField: 'id',
      text: 'ID'
    }, {
      dataField: 'todoName',
      text: 'Name'
    }, {
      dataField: 'todoDesc',
      text: 'Description'
    }, {
      dataField: 'completed',
      text: 'Completed',
      formatter: (cell, row) => (
        <div className="custom-switch custom-switch-off-danger custom-switch-on-success">
          <input
            type="checkbox"
            className="custom-control-input"
            defaultChecked={cell} />
          <label className="custom-control-label" />
        </div>
      )
    }, {
      dataField: 'createdAt',
      text: 'Created At'
    }, {
      dataField: 'Operations',
      text: 'Operations',
      formatter: (cellContent, row) => (
        <div className="checkbox disabled">
          <i onClick={this.onDeleteClick.bind(
            this,
            row.id
          )}
            className="fa fa-minus-circle pr-1" title="Delete" style={{ color: "red", cursor: "pointer" }} />
          <Link to={`/updateProject/${row.id}`} style={{ marginLeft: "10px" }} >
            <i className="fa fa-edit pr-1" title="Update" />
          </Link>
        </div>
      )
    },];

    return (
      <div className="content-wrapper">
        <div className="content-header">
          <div className="container-fluid">
            <Link to="/newtodo" className="btn btn-sm btn-primary">
              Create a New Todo
            </Link>
          </div>
        </div>

        <section className="content">
          <div className="container-fluid">
            <div className="row">
              <div className="col-12">
                <div className="card">
                  <div className="card-body">
                    {
                      <BootstrapTable
                        keyField='id'
                        remote
                        striped
                        hover
                        condensed
                        data={this.state.todo}
                        columns={columns}
                        noDataIndication="Table is Empty"
                        pagination={paginationFactory(this.options)} />
                    }
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
    );
  }
}

Todos.propTypes = {
  todo: PropTypes.object.isRequired,
  getUserTodos: PropTypes.func.isRequired,
  deleteTodo: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  todo: state.todo
});

export default connect(
  mapStateToProps,
  { getUserTodos, deleteTodo }
)(Todos);

