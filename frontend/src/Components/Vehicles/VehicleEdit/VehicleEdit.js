import React from 'react';
import {useNavigate} from 'react-router-dom';

const VehicleEdit = (props) => {

    const navigate = useNavigate();
    const [formData, updateFormData] = React.useState({
        registration: "",
        brand: "",
        model: "",
        color: 0,
        fuel: 0,
        year: "",
        price: "",
        location: 0,
        picture: ""
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();

        const registration = formData.registration !== "" ? formData.registration : props.vehicle.registration;
        const brand = formData.brand !== "" ? formData.brand : props.vehicle.brand;
        const model = formData.model !== "" ? formData.model : props.vehicle.model;
        const color = formData.color !== 0 ? formData.color : props.vehicle.color;
        const fuel = formData.fuel !== 0 ? formData.fuel : props.vehicle.fuel;
        const year = formData.year !== "" ? formData.year : props.vehicle.year;
        const price = formData.price !== "" ? formData.price : props.vehicle.price;
        const location = formData.location !== 0 ?  formData.location : props.vehicle.location.id;
        const picture = formData.picture !== "" ? formData.picture : props.vehicle.picture;

        props.onEditVehicle(props.vehicle.registration, brand, model, color, fuel, year, price, location, picture);
        navigate("/vehicles");
    }

    return (
        <div className="row mt-5 justify-content-center">
            <div className="col-md-6">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group mb-2">
                        <label htmlFor="registration">Registration</label>
                        <input type="text"
                               className="form-control"
                               id="registration"
                               name="registration"
                               placeholder={props.vehicle.registration}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group mb-2">
                        <label htmlFor="brand">Brand</label>
                        <input type="text"
                               className="form-control"
                               id="brand"
                               name="brand"
                               placeholder={props.vehicle.brand}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group mb-2">
                        <label htmlFor="model">Model</label>
                        <input type="text"
                               className="form-control"
                               id="model"
                               name="model"
                               placeholder={props.vehicle.model}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group mb-2">
                        <label htmlFor="price">Price</label>
                        <input type="text"
                               className="form-control"
                               id="price"
                               name="price"
                               placeholder={props.vehicle.price}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group mb-2">
                        <label htmlFor="year">Year</label>
                        <input type="text"
                               className="form-control"
                               id="year"
                               name="year"
                               placeholder={props.vehicle.year}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group mb-2">
                        <label htmlFor="picture">Picture</label>
                        <input type="text"
                               className="form-control"
                               id="picture"
                               name="picture"
                               placeholder={props.vehicle.picture}
                               onChange={handleChange}
                        />
                    </div>

                    <div className="form-group mb-2">
                        <label>Locations</label>
                        <select name="location" className="form-control" onChange={handleChange}>
                            {props.locations.map((location) => {
                                if (props.vehicle.location !== undefined && props.vehicle.location.id === location.id)
                                    return <option selected={props.vehicle.location.id} key={location.id}
                                                   value={location.id}>{location.name}, {location.street}/{location.streetNumber}</option>
                                else
                                    return <option key={location.id}
                                                   value={location.id}>{location.name}, {location.street}/{location.streetNumber}</option>
                            })}
                        </select>
                    </div>

                    <div className="form-group mb-2">
                        <label>Color</label>
                        <select name="color" className="form-control" onChange={handleChange}>
                            {props.colors.map((color, index) => {
                                    <option key={color} value={color}>{color}</option>
                                    if (props.vehicle.color !== undefined && props.vehicle.color === color)
                                        return <option selected={props.vehicle.color} key={color}
                                                       value={color}>{color}</option>
                                    else
                                        return <option key={color}
                                                       value={color}>{color}</option>
                                }
                            )}
                        </select>
                    </div>
                    <div className="form-group mb-2">
                        <label>Fuel</label>
                        <select name="fuel" className="form-control" onChange={handleChange}>
                            {props.fuels.map((fuel, index) => {
                                    <option key={fuel} value={fuel}>{fuel}</option>
                                    if (props.vehicle.fuel !== undefined && props.vehicle.fuel === fuel)
                                        return <option selected={props.vehicle.fuel} key={fuel}
                                                       value={fuel}>{fuel}</option>
                                    else
                                        return <option key={fuel}
                                                       value={fuel}>{fuel}</option>
                                }
                            )}
                        </select>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default VehicleEdit;