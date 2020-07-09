import React, { Component } from 'react'
import { Link } from "react-router-dom";

export class PageNotFound extends Component {

    render() {
        return (
            <div class="content-wrapper">
                <section class="content">
                    <div class="error-page">
                        <h2 class="headline text-warning"> 404</h2>
                        <div class="error-content">
                            <h3><i class="fas fa-exclamation-triangle text-warning"></i> Oops! Page not found.</h3>
                            <p>
                                We could not find the page you were looking for.
                                Meanwhile, you may &nbsp;
                                <Link to="/">
                                    return to dashboard 
                                </Link>.
                            </p>
                        </div>
                    </div>
                </section>
            </div>
        )
    }
}

export default PageNotFound


