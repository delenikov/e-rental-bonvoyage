import React from 'react';
import {useNavigate} from 'react-router-dom';
import BonVoyageService from "../../Repository/BonVoyageRepository";


const Login = (props) => {

    function parseJwt(token) {
        if (!token) {
            return;
        }
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace('-', '+').replace('_', '/');
        return JSON.parse(window.atob(base64));
    }

    const navigate = useNavigate();
    const [formData, updateFormData] = React.useState({
        username: "",
        password: ""
    })

    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault();
        BonVoyageService.login(formData.username, formData.password).then(resp => {
            localStorage.setItem("JWT", resp.data);
            props.onLogin()
            var array = localStorage.getItem("JWT");
            array = array.toString();
            array = array.split(" ");
            var jwt = array[1];
            var object = parseJwt(jwt);
            localStorage.setItem("role", object.role);
            localStorage.setItem("username", object.username);
            navigate("/vehicles");
        })
    }

    return (
        <div className="row mt-5 justify-content-center">
            <div className="col-md-5">
                <h4 className="text-center">Login Page</h4>
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="username">Username</label>
                        <input type="text"
                               className="form-control"
                               name="username"
                               required
                               placeholder="Enter username"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group mt-2">
                        <label htmlFor="price">Password</label>
                        <input type="password"
                               className="form-control"
                               name="password"
                               placeholder="Enter password"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <br/>
                    <div className="row">
                        <div className="col-12">
                            <button id="submit" type="submit" className="btn btn-primary w-100">Submit</button>
                        </div>
                    </div>
                    <hr/>
                    <h6 className="text-center">Dont have an account? Click below to register: </h6>
                    <div className="row">
                        <div className="col-12">
                            <a className="btn btn-success w-100" href="/register"> Go to Register </a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default Login;
