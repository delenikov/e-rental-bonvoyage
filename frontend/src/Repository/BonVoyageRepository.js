import axios from '../custom-axios/axios';

const BonVoyageService = {

    fetchVehicles: () => {
        return axios.get("/vehicles");
    },
    fetchLocations: () => {
        return axios.get("/locations");
    },
    fetchCart: () => {
        let username = localStorage.getItem("username");
        return axios.get("/cart", {
            params: {
                username: username
            }
        });
    },
    fetchCartVehicles: () => {
        let username = localStorage.getItem("username");
        return axios.get("/cart/vehicles", {
            params: {
                username: username
            }
        });
    },
    fetchRoles: () => {
        return axios.get("/roles");
    },
    fetchColors: () => {
        return axios.get("/vehicles/colors");
    },
    fetchFuels: () => {
        return axios.get("/vehicles/fuels");
    },
    addVehicle: (registration, brand, model, color, fuel, year, price, location, picture) => {
        return axios.post("/vehicles/add", {
            "registration": registration,
            "brand": brand,
            "model": model,
            "color": color,
            "fuel": fuel,
            "year": year,
            "price": price,
            "location": location,
            "picture": picture
        });
    },
    editVehicle: (registration, brand, model, color, fuel, year, price, location, picture) => {
        return axios.put(`/vehicles/edit/${registration}`, {
            "registration": registration,
            "brand": brand,
            "model": model,
            "color": color,
            "fuel": fuel,
            "year": year,
            "price": price,
            "location": location,
            "picture": picture
        });
    },
    deleteVehicle: (id) => {
        return axios.delete(`/vehicles/delete/${id}`);
    },
    getVehicle: (id) => {
        return axios.get(`/vehicles/${id}`);
    },
    addToCart: (id) => {
        let username = localStorage.getItem("username");
        return axios.post(`/vehicles/add-to-cart`, {
            "username": username,
            "registration": id
        });
    },
    removeFromCart: (id) => {
        let username = localStorage.getItem("username");
        return axios.delete(`/cart/remove-vehicle`, {
            params: {
                username: username,
                registration: id
            },
        });
    },
    login: (username, password) => {
        return axios.post("/api/login", {
            "username": username,
            "password": password
        });
    },
    register: (username, password, repeatPassword, name, surname, role) => {
        return axios.post("/register", {
            "username": username,
            "password": password,
            "repeatPassword": repeatPassword,
            "name": name,
            "surname": surname,
            "role": role
        });
    },

    fetchVehiclesByLocation: (id) => {
        return axios.get(`/locations/${id}/vehicles`);
    }


}

export default BonVoyageService;