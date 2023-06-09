import React from 'react';
import {Link} from 'react-router-dom';

const header = (props) => {
    let authenticate

    return (
        <header>
            <nav className="navbar navbar-expand-xl navbar-dark navbar-fixed bg-dark">
                <a className="navbar-brand mx-3" href="/books">E-Biblioteka</a>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                        aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarCollapse">
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item active">
                            <Link className="nav-link" to={"/books"}>Books</Link>
                        </li>
                        <li className="nav-item active">
                            <Link className="nav-link" to={"/categories"}>Categories</Link>
                        </li>
                    </ul>
                    <form className="form-inline mt-2 mt-md-0 ml-3">
                        {authenticate}
                    </form>
                </div>
            </nav>
        </header>
    )
}

export default header;