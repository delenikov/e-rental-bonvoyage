package com.example.bonvoyage.Service.Implementation;

import com.example.bonvoyage.Enumeration.Color;
import com.example.bonvoyage.Enumeration.Fuel;
import com.example.bonvoyage.Exceptions.LocationNotFoundException;
import com.example.bonvoyage.Exceptions.VehicleNotFoundException;
import com.example.bonvoyage.Models.DTOs.VehicleDto;
import com.example.bonvoyage.Models.Location;
import com.example.bonvoyage.Models.Vehicle;
import com.example.bonvoyage.Repository.LocationRepository;
import com.example.bonvoyage.Repository.VehicleRepository;
import com.example.bonvoyage.Service.VehicleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final LocationRepository locationRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, LocationRepository locationRepository) {
        this.vehicleRepository = vehicleRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Vehicle> findAll() {
        return this.vehicleRepository.findAll();
    }

    @Override
    public Optional<Vehicle> findById(String registration) {
        return this.vehicleRepository.findById(registration);
    }

    @Override
    public List<Vehicle> findAllByLocationId(Integer id) {
        return this.vehicleRepository.findAllByLocationId(id);
    }

    @Override
    public Optional<Vehicle> save(String registration, String brand, String model, Color color, Fuel fuel, String year, Integer price, Integer locationId, String picture) {
        if (this.vehicleRepository.findById(registration).isPresent()) {
            this.vehicleRepository.deleteById(registration);
        }
        Location location = this.locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));
        Vehicle vehicle = new Vehicle(registration, brand, model, color, fuel, year, price, location, picture);
        this.vehicleRepository.save(vehicle);
        return Optional.of(vehicle);
    }

    @Override
    public Optional<Vehicle> save(VehicleDto vehicleDto) {
        if (this.vehicleRepository.findById(vehicleDto.getRegistration()).isPresent()) {
            this.vehicleRepository.deleteById(vehicleDto.getRegistration());
        }
        Location location = this.locationRepository.findById(vehicleDto.getLocation()).orElseThrow(() -> new LocationNotFoundException(vehicleDto.getLocation()));
        Vehicle vehicle = new Vehicle(vehicleDto.getRegistration(), vehicleDto.getBrand(), vehicleDto.getModel(), vehicleDto.getColor(), vehicleDto.getFuel(), vehicleDto.getYear(), vehicleDto.getPrice(), location, vehicleDto.getPicture());
        this.vehicleRepository.save(vehicle);
        return Optional.of(vehicle);
    }

    @Override
    public Optional<Vehicle> edit(String registration, String brand, String model, Color color, Fuel fuel, String year, Integer price, Integer locationId, String picture) {
        Vehicle vehicle = this.vehicleRepository.findById(registration).orElseThrow(() -> new VehicleNotFoundException(registration));
        Location location = this.locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));
        vehicle.setBrand(brand);
        vehicle.setModel(model);
        vehicle.setColor(color);
        vehicle.setFuel(fuel);
        vehicle.setYear(year);
        vehicle.setPrice(price);
        vehicle.setLocation(location);
        vehicle.setPicture(picture);
        this.vehicleRepository.save(vehicle);
        return Optional.of(vehicle);
    }

    @Override
    public Optional<Vehicle> edit(String registration, VehicleDto vehicleDto) {
        Vehicle vehicle = this.vehicleRepository.findById(registration).orElseThrow(() -> new VehicleNotFoundException(registration));
        Location location = this.locationRepository.findById(vehicleDto.getLocation()).orElseThrow(() -> new LocationNotFoundException(vehicleDto.getLocation()));
        vehicle.setBrand(vehicleDto.getBrand());
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setColor(vehicleDto.getColor());
        vehicle.setFuel(vehicleDto.getFuel());
        vehicle.setYear(vehicleDto.getYear());
        vehicle.setPrice(vehicleDto.getPrice());
        vehicle.setLocation(location);
        vehicle.setPicture(vehicleDto.getPicture());
        this.vehicleRepository.save(vehicle);
        return Optional.of(vehicle);
    }

    @Override
    public void deleteById(String registration) {
        this.vehicleRepository.deleteById(registration);
    }
}
