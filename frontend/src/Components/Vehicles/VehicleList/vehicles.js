import React from "react";
import {Link} from "react-router-dom";
import ReactPaginate from "react-paginate";
import VehicleTerm from "../VehicleTerm/VehicleTerm";


class Vehicles extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            page: 0,
            size: 4
        }
    }

    render() {
        let username = localStorage.getItem("username");
        let role = localStorage.getItem("role");

        let adminAddVehicle, adminEditColumn, adminDeleteColumn;
        if (role === "ROLE_ADMIN" && role != null) {
            adminAddVehicle = (<div className="col mt-5 mb-3">
                <div className="row">
                    <div className="col-sm-12 col-md-12">
                        <Link className={"btn btn-info"} to={"/vehicles/add"}>Add new vehicle</Link>
                    </div>
                </div>
            </div>);
            adminEditColumn = (<th scope={"col"}>Edit</th>);
            adminDeleteColumn = (<th scope={"col"}>Delete</th>);
        }

        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.vehicles.length / this.state.size);
        const vehicles = this.getVehiclesPage(offset, nextPageOffset);

        return (

            <div className={"container mb-4"}>
                {adminAddVehicle}
                <div className={"row"}>
                    <table className={"table table-striped text-center"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Image</th>
                            <th scope={"col"}>Brand & Model</th>
                            <th scope={"col"}>Price</th>
                            <th scope={"col"}>Color</th>
                            <th scope={"col"}>Year</th>
                            <th scope={"col"}>Fuel</th>
                            <th scope={"col"}>Location name</th>
                            <th scope={"col"}>Street</th>
                            <th scope={"col"}>Add to Cart</th>
                            {adminEditColumn}
                            {adminDeleteColumn}
                        </tr>
                        </thead>
                        <tbody className="align-middle">
                        {vehicles}
                        </tbody>
                    </table>
                </div>
                <ReactPaginate color="primary"
                               previousLabel={"Prev"}
                               previousClassName={"page-item"}
                               previousLinkClassName={"page-link"}
                               nextLabel={"Next"}
                               nextClassName={"page-item"}
                               nextLinkClassName={"page-link"}
                               breakLabel={<a href="/#">...</a>}
                               breakClassName={"break-me"}
                               pageClassName={"page-item"}
                               pageLinkClassName={"page-link"}
                               pageCount={pageCount}
                               marginPagesDisplayed={2}
                               pageRangeDisplayed={5}
                               onPageChange={this.handlePageClick}
                               containerClassName={"pagination m-4 justify-content-center"}
                               activeClassName={"active"}/>
            </div>
        );
    }

    handlePageClick = (data) => {
        let selected = data.selected;
        this.setState({
            page: selected
        })
    }

    getVehiclesPage = (offset, nextPageOffset) => {
        return this.props.vehicles.map((vehicle) => {
            return (
                <VehicleTerm key={vehicle.registration} vehicle={vehicle} onAddToCart={this.props.onAddToCart}
                             onDelete={this.props.onDelete} onEdit={this.props.onEdit}/>
            );
        }).filter((veh, index) => {
            return index >= offset && index < nextPageOffset;
        })
    }

}

export default Vehicles;
