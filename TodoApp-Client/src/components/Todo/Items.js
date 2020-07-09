import React, { Component } from 'react'
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { deleteItem, getUserItems } from "../../actions/itemActions"
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.css';
import 'react-bootstrap-table2-paginator/dist/react-bootstrap-table2-paginator.min.css';

import BootstrapTable from 'react-bootstrap-table-next';
import paginationFactory from 'react-bootstrap-table2-paginator';


export class Items extends Component {


    constructor(props) {
        super(props);
        this.state = {
            page: 0,
            sizePerPage: 10,
            errors: {},
            items: []
        };
        //{id: 1, todoName: "qw dq", todoDesc: "qwdqwdqwdqwdqwd"}
        this.options = {
            page: 1,
            paginationSize: 4,
            pageStartIndex: 1,
            totalSize: this.props.item.items.total,
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

        const { items } = nextProps.item;
        if (items.length !== 0) {
            this.options.totalSize = items.total;
            this.setState({ items: items.content });
        }

    }

    componentDidMount() {
        this.props.getUserItems(this.state.page, this.state.sizePerPage);
        const { items } = this.props.item;
        if (items.length !== 0) {
            this.options.totalSize = items.total;
            this.setState({ items: items.content });
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
        this.props.getUserItems(page, sizePerPage);
    }

    render() {

        //const { items } = this.props.item;
        const columns = [{
            dataField: 'id',
            text: 'ID'
        }, {
            dataField: 'itemName',
            text: 'Name'
        }, {
            dataField: 'itemDesc',
            text: 'Description'
        }, {
            dataField: 'completed',
            text: 'Completed',
            formatter: (cell, row) => (
                <div className="custom-switch custom-switch-off-danger custom-switch-on-success">
                    {cell}
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
                    <Link to={`/updateitem/${row.id}`} style={{ marginLeft: "10px" }} >
                        <i className="fa fa-edit pr-1" title="Update" />
                    </Link>
                </div>
            )
        },];

        return (
            <div className="content-wrapper">
                <div className="content-header">
                    <div className="container-fluid">
                        <Link to="/newitem" className="btn btn-sm btn-primary">
                            Create a New Todo Item
                        </Link>
                    </div>
                </div>
                <section className="content">
                    <div className="container-fluid">
                        <div className="row">
                            <div className="col-12">
                                <div className="card">
                                    <div className="card-body">
                                        <BootstrapTable
                                            keyField='id'
                                            remote
                                            striped
                                            hover
                                            condensed
                                            data={this.state.items}
                                            columns={columns}
                                            noDataIndication="Table is Empty"
                                            pagination={paginationFactory(this.options)} />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        )
    }
}


Items.propTypes = {
    item: PropTypes.object.isRequired,
    getUserItems: PropTypes.func.isRequired,
    deleteItem: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
    item: state.item,
});

export default connect(
    mapStateToProps,
    { getUserItems, deleteItem }
)(Items);