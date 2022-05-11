import React, {useState} from 'react';
import {Link, useNavigate} from 'react-router-dom';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {faCartPlus} from '@fortawesome/free-solid-svg-icons'
import Calendar from 'react-calendar'
import VehicleTerm from "../Vehicles/VehicleTerm/VehicleTerm";
import getActiveStartDate from "react-calendar/dist/umd/Calendar";

const cartPlus = <FontAwesomeIcon icon={faCartPlus}/>


const Cart = (props) => {

    let username = localStorage.getItem("username");

    const navigate = useNavigate();

    const [vehicles, setVehicles] = useState(props.cartVehicles);
    React.useEffect(() => {
            setVehicles(props.cartVehicles);
        }, [props.cartVehicles]
    )

    function deleteVehicleFromCart(registration) {
        props.onDeleteFromCart(registration);
        //window.location.reload();
    }

    var priceToPay = 0;
    for(const veh of props.cartVehicles) {
        priceToPay += veh.price;
    }

    const [value, onChange] = useState(new Date());
    var start;
    var end;
    var array = value.toString();
    array = array.split(",");
    start = array[0];
    end = array[1];

    return (
        <div>
            <div className="row">
                <h3 className="text-center">{username}'s shopping cart</h3>
                <div className="row">{vehicles.map((vehicle) => {
                        return (
                            <div className="card rounded m-2" style={{width: '300px'}}>
                                <img className="card-img-top mt-2" src={vehicle.picture} alt="Card image cap"/>
                                <div className="card-body">
                                    <h5 className="card-title">{vehicle.brand} {vehicle.model}</h5>
                                    <p className="card-text">Price: {vehicle.price} mkd per day</p>
                                </div>
                                <ul className="list-group list-group-flush">
                                    <li className="list-group-item">Color: {vehicle.color} </li>
                                    <li className="list-group-item">Fuel: {vehicle.fuel}</li>
                                    <li className="list-group-item">Year: {vehicle.year}</li>
                                    <li className="list-group-item">Location: {vehicle.location.name}, {vehicle.location.street}/{vehicle.location.streetNumber} </li>
                                </ul>
                                <div className="card-body">
                                    <div className="row">
                                        <div className="col-12">
                                            <a href="#" className="card-link btn btn-danger w-100"
                                               onClick={() => deleteVehicleFromCart(vehicle.registration)}>Remove</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        );
                    }
                )}</div>
                <hr/>
                <div className="row">
                    <div className="col-6">
                        <Calendar onChange={onChange} value={value} selectRange={true} />
                    </div>
                    <div className="col-6 border border-primary rounded">
                        <div className="mt-3">
                            <h4 className="display-6">Total to pay: {priceToPay} mkd</h4>
                            <h4 className="display-6">Rent from:</h4> <p>{start}</p>
                            <h4 className="display-6">Rent until:</h4> <p>{end}</p>
                        </div>
                        <hr/>
                            <button className="card-link btn btn-info w-100 mb-3">Proceed to pay</button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Cart;