package com.example.bonvoyage.Models;

import com.example.bonvoyage.Enumeration.Color;
import com.example.bonvoyage.Enumeration.Fuel;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Vehicle {

    @Id
    @Column(columnDefinition = "char(8)", length = 8)
    private String registration;
    private String brand;
    private String model;

    @Enumerated(EnumType.STRING)
    private Color color;
    @Enumerated(EnumType.STRING)
    private Fuel fuel;

    private String year;
    private Integer price;

    @ManyToOne
    private Location location;

    private String picture;


    public Vehicle(String registration, String brand, String model, Color color, Fuel fuel, String year, Integer price, Location location, String picture) {
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

    public Vehicle(String registration, String brand, String model, Color color, Fuel fuel, String year, Integer price, Location location) {
        this.registration = registration;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.fuel = fuel;
        this.year = year;
        this.price = price;
        this.location = location;
    }

    public Vehicle() {
    }
}
