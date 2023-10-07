package com.example.bonvoyage.Controllers;

import com.example.bonvoyage.Enumeration.Color;
import com.example.bonvoyage.Enumeration.Fuel;
import com.example.bonvoyage.Models.DTOs.VehicleDto;
import com.example.bonvoyage.Models.DTOs.VehicleToCartDTO;
import com.example.bonvoyage.Models.ShoppingCart;
import com.example.bonvoyage.Models.Vehicle;
import com.example.bonvoyage.Service.ShoppingCartService;
import com.example.bonvoyage.Service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@RestController
@RequestMapping(value = {"/vehicles"})
public class VehicleController {

    private final VehicleService vehicleService;
    private final ShoppingCartService shoppingCartService;

    public VehicleController(VehicleService vehicleService, ShoppingCartService shoppingCartService) {
        this.vehicleService = vehicleService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    private List<Vehicle> findAll() {
        return this.vehicleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> findById(@PathVariable String id) {
        return this.vehicleService.findById(id).map(vehicle -> ResponseEntity.ok().body(vehicle)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Vehicle> save(@RequestBody VehicleDto VehicleDto) {
        return this.vehicleService.save(VehicleDto)
                .map(vehicle -> ResponseEntity.ok().body(vehicle))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Vehicle> save(@PathVariable String id, @RequestBody VehicleDto VehicleDto) {
        return this.vehicleService.edit(id, VehicleDto)
                .map(vehicle -> ResponseEntity.ok().body(vehicle))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable String id) {
        this.vehicleService.deleteById(id);
        if (this.vehicleService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/colors")
    private List<Color> listAllVehicleColors() {
        List<Color> colors = new ArrayList<Color>(EnumSet.allOf(Color.class));
        return colors;
    }

    @GetMapping("/fuels")
    private List<Fuel> listAllFuelTypes() {
        List<Fuel> fuels = new ArrayList<Fuel>(EnumSet.allOf(Fuel.class));
        return fuels;
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<ShoppingCart> save(@RequestBody VehicleToCartDTO vehicleToCartDTO) {
        return this.shoppingCartService.addVehicleToShoppingCart(vehicleToCartDTO)
                .map(object -> ResponseEntity.ok().body(object))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
