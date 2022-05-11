import logo from '../../logo.svg';
import './App.css';
import {Component} from "react";
import {Routes, Route, BrowserRouter} from "react-router-dom";
import BonVoyageService from "../../Repository/BonVoyageRepository";
import Header from "../Header/header";
import Vehicles from "../Vehicles/VehicleList/vehicles";
import Locations from "../Locations/locations";
import Register from "../Register/register";
import Login from "../Login/login";
import Footer from "../Footer/footer";
import VehicleAdd from "../Vehicles/VehicleAdd/VehicleAdd";
import VehicleEdit from "../Vehicles/VehicleEdit/VehicleEdit";
import Cart from "../Cart/Cart";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            vehicles: [],
            locations: [],
            fuels: [],
            colors: [],
            roles: [],
            cart: [],
            cartVehicles: [],
            selectedVehicle: {},
            vehiclesByLocation: []
        }
    }

    componentDidMount() {
        this.loadRoles();
        this.loadVehicles();
        this.loadLocations();
        this.loadColors();
        this.loadFuels();
    }

    fetchData = () => {
        this.loadCart();
        this.loadCartVehicles();
    }

    reloadUserData = () => {
        this.loadCart();
        this.loadCartVehicles();
    }

    loadVehicles = () => {
        BonVoyageService.fetchVehicles()
            .then((data) => {
                this.setState({
                    vehicles: data.data
                })
            });
    }

    loadLocations = () => {
        BonVoyageService.fetchLocations()
            .then((data) => {
                this.setState({
                    locations: data.data
                })
            })
    }

    loadColors = () => {
        BonVoyageService.fetchColors()
            .then((data) => {
                this.setState({
                    colors: data.data
                })
            })
    }

    loadFuels = () => {
        BonVoyageService.fetchFuels()
            .then((data) => {
                this.setState({
                    fuels: data.data
                })
            })
    }

    loadRoles = () => {
        BonVoyageService.fetchRoles()
            .then((data) => {
                this.setState({
                    roles: data.data
                })
            })
    }

    getVehicle = (id) => {
        BonVoyageService.getVehicle(id)
            .then((data) => {
                this.setState({
                    selectedVehicle: data.data
                })
            })
    }

    deleteVehicle = (id) => {
        BonVoyageService.deleteVehicle(id)
            .then(() => {
                this.loadVehicles();
            })
    }

    addVehicle = (registration, brand, model, color, fuel, year, price, location, picture) => {
        BonVoyageService.addVehicle(registration, brand, model, color, fuel, year, price, location, picture)
            .then(() => {
                this.loadVehicles();
            })
    }

    editVehicle = (registration, brand, model, color, fuel, year, price, location, picture) => {
        BonVoyageService.editVehicle(registration, brand, model, color, fuel, year, price, location, picture)
            .then(() => {
                this.loadVehicles();
            })
    }

    loadCart = () => {
        BonVoyageService.fetchCart()
            .then((data) => {
                this.setState({
                    cart: data.data,
                })
                this.loadCartVehicles();
            })
    }

    loadCartVehicles = () => {
        BonVoyageService.fetchCartVehicles()
            .then((data) => {
                this.setState({
                    cartVehicles: data.data
                })
            })
    }

    addVehicleToCart = (id) => {
        BonVoyageService.addToCart(id)
            .then(() => {
                this.loadCart();
                this.loadCartVehicles();
            })
    }

    removeVehicleFromCart = (id) => {
        BonVoyageService.removeFromCart(id)
            .then(() => {
                this.loadCart();
                this.loadCartVehicles();
            })
    }

    loadVehiclesByLocation = (id) => {
        BonVoyageService.fetchVehiclesByLocation(id)
            .then((data) => {
                this.setState({
                    vehiclesByLocation: data.data
                })
            });

    }

    render() {
        return (
            <BrowserRouter>
                <Header/>
                <main>
                    <div className={"container"}>
                        <Routes>
                            {["/", "/home"].map((path) => {
                                return (<Route path={path} key={path}
                                               element={<Footer locations={this.state.locations}
                                                                loadVehicles={this.loadVehiclesByLocation}/>}/>)
                            })}
                            <Route path="/vehicles/add"
                                   element={<VehicleAdd locations={this.state.locations}
                                                        categories={this.state.categories}
                                                        fuels={this.state.fuels}
                                                        colors={this.state.colors}
                                                        onAddVehicle={this.addVehicle}/>}/>
                            <Route path="/vehicles/edit/:id"
                                   element={<VehicleEdit locations={this.state.locations}
                                                         onEditVehicle={this.editVehicle}
                                                         fuels={this.state.fuels}
                                                         colors={this.state.colors}
                                                         onEditVehicle={this.editVehicle}
                                                         vehicle={this.state.selectedVehicle}/>}/>
                            <Route path="/vehicles" element={<Vehicles vehicles={this.state.vehicles}
                                                                       onAddToCart={this.addVehicleToCart}
                                                                       onDelete={this.deleteVehicle}
                                                                       onEdit={this.getVehicle}/>}/>
                            <Route path="/register" element={<Register roles={this.state.roles}/>}/>
                            <Route path="/login" element={<Login onLogin={this.fetchData}/>}/>
                            <Route path="/locations" element={<Locations locations={this.state.locations}/>}/>
                            <Route path="/cart"
                                   element={<Cart cart={this.state.cart} cartVehicles={this.state.cartVehicles}
                                                  onDeleteFromCart={this.removeVehicleFromCart}/>}/>
                            <Route path="/locations/:id/vehicles"
                                   element={<Vehicles vehicles={this.state.vehiclesByLocation}
                                                      onDelete={this.deleteVehicle}
                                                      onEdit={this.getVehicle}/>}/>
                        </Routes>
                    </div>
                </main>
            </BrowserRouter>
        );
    }
}

export default App;