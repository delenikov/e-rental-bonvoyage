import React from 'react';
import {Link} from 'react-router-dom';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faCartShopping} from '@fortawesome/free-solid-svg-icons'

const element = <FontAwesomeIcon icon={faCartShopping}/>

const header = (props) => {

    let username = localStorage.getItem("username");
    let role = localStorage.getItem("role");

    let authenticate;
    if (localStorage.getItem("JWT")) {
        authenticate = (<a className="btn btn-outline-info my-2 my-sm-0" href="/login"
                           onClick={() => localStorage.removeItem("JWT")}>Logout</a>
        );
    } else {
        authenticate = (<Link className="btn btn-outline-info my-2 my-sm-0" to={"/login"}>Login</Link>);
    }


    return (
        <header>
            <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                <div className="container-fluid">
                    <a className="navbar-brand" href="/home">E-Rental BonVoyage</a>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                            <li className="nav-item">
                                <Link className="nav-link" to={"/vehicles"}>Vehicles</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to={"/locations"}>Locations</Link>
                            </li>
                        </ul>
                        <form className="d-flex">
                            <Link className="btn btn-outline-success me-2" to={"/cart"}> Cart {element}</Link>
                            {authenticate}
                        </form>
                    </div>
                </div>
            </nav>
        </header>
    )
}

export default header