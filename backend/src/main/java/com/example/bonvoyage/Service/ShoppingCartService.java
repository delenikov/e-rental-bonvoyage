package com.example.bonvoyage.Service;


import com.example.bonvoyage.Models.DTOs.VehicleToCartDTO;
import com.example.bonvoyage.Models.ShoppingCart;
import com.example.bonvoyage.Models.Vehicle;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {

    List<Vehicle> listAllVehiclesInShoppingCart(Long cartId);
    Optional<ShoppingCart> getActiveShoppingCart(String username);
    Optional<ShoppingCart> addVehicleToShoppingCart(VehicleToCartDTO vehicleToCartDTO);
    void deleteVehicleToShoppingCart(String username, String registration);
}
