package com.example.bonvoyage.Models.DTOs;

import com.example.bonvoyage.Enumeration.Color;
import com.example.bonvoyage.Enumeration.Fuel;
import lombok.Data;


@Data
public class VehicleDto {

    private String registration;
    private String brand;
    private String model;

    private Color color;
    private Fuel fuel;

    private String year;
    private Integer price;
    private Integer location;
    private String picture;

    public VehicleDto(String registration, String brand, String model, Color color, Fuel fuel, String year, Integer price, Integer location, String picture) {
        this.registration = registration;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.fuel = fuel;
        this.year = year;
        this.price = price;
        this.location = location;
        this.picture = picture;
    }

    public VehicleDto() {
    }
}
