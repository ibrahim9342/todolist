import React, { Component } from 'react'

export class Footer extends Component {
    render() {
        return (
            <footer className="main-footer" style={{minHeight:"50px"}}>
                <div className="float-right d-none d-sm-inline-block">
                    <b>
                        Version &nbsp;
                    </b>
                    0.0.1
                </div>
            </footer>
        )
    }
}
export default Footer
