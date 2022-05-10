package com.example.bonvoyage.Service;

import com.example.bonvoyage.Enumeration.Color;
import com.example.bonvoyage.Enumeration.Fuel;
import com.example.bonvoyage.Models.DTOs.VehicleDto;
import com.example.bonvoyage.Models.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    List<Vehicle> findAll();
    Optional<Vehicle> findById(String registration);
    List<Vehicle> findAllByLocationId(Integer id);
    Optional<Vehicle> save(String registration, String brand, String model, Color color, Fuel fuel, String year, Integer price, Integer locationId, String picture);
    Optional<Vehicle> save(VehicleDto vehicleDto);
    Optional<Vehicle> edit(String registration, String brand, String model, Color color, Fuel fuel, String year, Integer price, Integer locationId, String picture);
    Optional<Vehicle> edit(String registration, VehicleDto vehicleDto);
    void deleteById(String registration);
}
