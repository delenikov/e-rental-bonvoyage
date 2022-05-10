package com.example.bonvoyage.Models.DTOs;

import lombok.Data;

@Data
public class VehicleToCartDTO {
    String username;
    String registration;

    public VehicleToCartDTO(String username, String registration) {
        this.username = username;
        this.registration = registration;
    }

    public VehicleToCartDTO() {
    }
}
