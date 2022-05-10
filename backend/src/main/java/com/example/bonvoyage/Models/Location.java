package com.example.bonvoyage.Models;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;

@Data
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String name;
    private String street;
    private String streetNumber;

    private Time open;
    private Time close;

    public Location(Integer id, String name, String street, String streetNumber, Time open, Time close) {
        Id = id;
        this.name = name;
        this.street = street;
        this.streetNumber = streetNumber;
        this.open = open;
        this.close = close;
    }

    public Location() {
    }
}
