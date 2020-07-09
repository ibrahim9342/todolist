import React, { Component } from 'react'

export class AllTodos extends Component {
    render() {
        return (
            <div className="content-wrapper">
                <div className="content-header">
                    <div className="container-fluid">
                        <div className="row mb-2">
                            <div className="col-sm-6">
                                <h1 className="m-0 text-dark">All Todo</h1>
                            </div>
                        </div>
                    </div>
                </div>
                <section className="content">
                    <div className="container">
                        All Todo
                    </div>
                </section>
            </div>
        )
    }
}

export default AllTodos
