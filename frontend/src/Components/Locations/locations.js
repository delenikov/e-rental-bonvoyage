
import React from "react";

const locations = (props) => {
    return (
        <div className={"container mb-4"}>
            <div className={"row"}>
                <div className={"row"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Name</th>
                            <th scope={"col"}>Street & Street Number</th>
                            <th scope={"col"}>Working hours</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.locations.map((location) => {
                            return (
                                <tr key={location.name}>
                                    <td>{location.name}</td>
                                    <td>{location.street} - {location.streetNumber}</td>
                                    <td>{location.open} - {location.close}</td>
                                </tr>
                            );
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}

export default locations;
