package com.example.bonvoyage.Controllers;

import com.example.bonvoyage.Models.ShoppingCart;
import com.example.bonvoyage.Models.User;
import com.example.bonvoyage.Models.Vehicle;
import com.example.bonvoyage.Service.ShoppingCartService;
import com.example.bonvoyage.Service.UserService;
import com.example.bonvoyage.Service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/cart"})
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final VehicleService vehicleService;
    private final UserService userService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, VehicleService vehicleService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.vehicleService = vehicleService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ShoppingCart> getShoppingCart(@RequestParam String username) {
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username).get();
        return new ResponseEntity<ShoppingCart>(shoppingCart, HttpStatus.OK);
    }

    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getShoppingCartVehicles(@RequestParam String username) {
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username).get();
        List<Vehicle> VehiclesInShopping = this.shoppingCartService.listAllVehiclesInShoppingCart(shoppingCart.getId());
        return new ResponseEntity<List<Vehicle>>(VehiclesInShopping, HttpStatus.OK);
    }

    @DeleteMapping("/remove-vehicle")
    public ResponseEntity deleteById(@RequestParam(required = false, name = "username") String username, @RequestParam String registration) {
        User user = this.userService.findByUsername(username);
        Vehicle vehicle = this.vehicleService.findById(registration).get();
        this.shoppingCartService.deleteVehicleToShoppingCart(username, registration);
        if (!user.getShoppingCart().getVehicles().contains(vehicle))
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
