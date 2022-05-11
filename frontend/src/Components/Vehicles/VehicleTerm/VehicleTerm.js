import React from 'react';
import {Link} from 'react-router-dom';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faCartPlus} from '@fortawesome/free-solid-svg-icons'

const cartPlus = <FontAwesomeIcon icon={faCartPlus}/>


const VehicleTerm = (props) => {

    let username = localStorage.getItem("username");
    let role = localStorage.getItem("role");

    let adminEditButton, adminDeleteButton;
    if (role === "ROLE_ADMIN") {
        adminEditButton = (<td>
            <Link className={"btn btn-warning"} onClick={() => props.onEdit(props.vehicle.registration)}
                  to={`/vehicles/edit/${props.vehicle.registration}`}>Edit</Link>
        </td>);
        adminDeleteButton = (<td>
            <a title={"Delete"} className={"btn btn-danger"}
               onClick={() => props.onDelete(props.vehicle.registration)}>Delete</a>
        </td>);
    }

    return (
        <tr key={props.vehicle.registration}>
            <td style={{width: '300px'}}><img className="w-50" src={props.vehicle.picture}/></td>
            <td>{props.vehicle.brand} {props.vehicle.model}</td>
            <td>{props.vehicle.price}</td>
            <td>{props.vehicle.color}</td>
            <td>{props.vehicle.year}</td>
            <td>{props.vehicle.fuel}</td>
            <td>{props.vehicle.location.name}</td>
            <td>{props.vehicle.location.street} {props.vehicle.location.streetNumber} </td>
            <td>
                <a className={"btn btn-success"}
                   onClick={() => props.onAddToCart(props.vehicle.registration)}>Add {cartPlus}</a>
            </td>
            {adminEditButton}
            {adminDeleteButton}
        </tr>
    )
}

export default VehicleTerm;
