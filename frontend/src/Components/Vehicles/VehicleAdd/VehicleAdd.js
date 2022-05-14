import React from 'react';
import {useNavigate} from 'react-router-dom';

const VehicleAdd = (props) => {

    const navigate = useNavigate();
    const [formData, updateFormData] = React.useState({
        registration: "",
        brand: "",
        model: "",
        color: "White",
        fuel: "Diesel",
        year: "",
        price: "",
        location: 1,
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

        const registration = formData.registration;
        const brand = formData.brand;
        const model = formData.model;
        const color = formData.color;
        const fuel = formData.fuel;
        const year = formData.year;
        const price = formData.price;
        const location = formData.location;
        const picture = formData.picture;

        props.onAddVehicle(registration, brand, model, color, fuel, year, price, location, picture);
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
                               required
                               placeholder="Enter car registration"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group mb-2">
                        <label htmlFor="brand">Brand</label>
                        <input type="text"
                               className="form-control"
                               id="brand"
                               name="brand"
                               required
                               placeholder="Enter car brand"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group mb-2">
                        <label htmlFor="model">Model</label>
                        <input type="text"
                               className="form-control"
                               id="model"
                               name="model"
                               required
                               placeholder="Enter car model"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group mb-2">
                        <label htmlFor="price">Price</label>
                        <input type="text"
                               className="form-control"
                               id="price"
                               name="price"
                               required
                               placeholder="Enter rent price"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group mb-2">
                        <label htmlFor="year">Year</label>
                        <input type="text"
                               className="form-control"
                               id="year"
                               name="year"
                               required
                               placeholder="Car manufactured year"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group mb-2">
                        <label htmlFor="picture">Picture</label>
                        <input type="text"
                               className="form-control"
                               id="picture"
                               name="picture"
                               required
                               placeholder="Car picture link"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group mb-2">
                        <label>Color</label>
                        <select name="color" className="form-control" onChange={handleChange}>
                            {props.colors.map((color, index) =>
                                <option key={color} value={color}>{color}</option>
                            )}
                        </select>
                    </div>
                    <div className="form-group mb-2">
                        <label>Fuel</label>
                        <select name="fuel" className="form-control" onChange={handleChange}>
                            {props.fuels.map((fuel, index) =>
                                <option key={fuel} value={fuel}>{fuel}</option>
                            )}
                        </select>
                    </div>
                    <div className="form-group mb-2">
                        <label>Locations</label>
                        <select name="location" className="form-control" onChange={handleChange}>
                            {props.locations.map((location) =>
                                <option key={location.id}
                                        value={location.id}>{location.name}, {location.street}/{location.streetNumber}</option>
                            )}
                        </select>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default VehicleAdd;
