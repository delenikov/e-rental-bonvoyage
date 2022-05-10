package com.example.bonvoyage.Controllers;

import com.example.bonvoyage.Models.Location;
import com.example.bonvoyage.Models.Vehicle;
import com.example.bonvoyage.Service.LocationService;
import com.example.bonvoyage.Service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping(value = {"/locations"})
public class LocationController {

    private final LocationService locationService;
    private final VehicleService vehicleService;

    public LocationController(LocationService locationService, VehicleService vehicleService) {
        this.locationService = locationService;
        this.vehicleService = vehicleService;
    }

    @GetMapping
    private List<Location> findAllLocations() {
        return this.locationService.findAll();
    }

    @GetMapping("/{id}/vehicles")
    private List<Vehicle> findAllVehicleByLocation(@PathVariable Integer id) {
        return this.vehicleService.findAllByLocationId(id);
    }
}
