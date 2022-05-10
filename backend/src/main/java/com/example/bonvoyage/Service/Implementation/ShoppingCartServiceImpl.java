package com.example.bonvoyage.Service.Implementation;

import com.example.bonvoyage.Enumeration.ShoppingCartStatus;
import com.example.bonvoyage.Exceptions.ShoppingCartNotFoundException;
import com.example.bonvoyage.Exceptions.UserNotFoundException;
import com.example.bonvoyage.Exceptions.VehicleAlreadyInShoppingCartException;
import com.example.bonvoyage.Exceptions.VehicleNotFoundException;
import com.example.bonvoyage.Models.DTOs.VehicleToCartDTO;
import com.example.bonvoyage.Models.ShoppingCart;
import com.example.bonvoyage.Models.User;
import com.example.bonvoyage.Models.Vehicle;
import com.example.bonvoyage.Repository.ShoppingCartRepository;
import com.example.bonvoyage.Repository.UserRepository;
import com.example.bonvoyage.Repository.VehicleRepository;
import com.example.bonvoyage.Service.ShoppingCartService;
import com.example.bonvoyage.Service.VehicleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   UserRepository userRepository,
                                   VehicleRepository vehicleRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }


    @Override
    public List<Vehicle> listAllVehiclesInShoppingCart(Long cartId) {
        if (!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.shoppingCartRepository.findById(cartId).get().getVehicles();

    }

    @Override
    @Transactional
    public Optional<ShoppingCart> getActiveShoppingCart(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        ShoppingCart shoppingCart = this.shoppingCartRepository
                .findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });
        return Optional.of(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> addVehicleToShoppingCart(VehicleToCartDTO vehicleToCartDTO) {
        String username = vehicleToCartDTO.getUsername();
        String registration = vehicleToCartDTO.getRegistration();
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username).get();
        Vehicle vehicle = this.vehicleRepository.findById(registration)
                .orElseThrow(() -> new VehicleNotFoundException(registration));
        if (shoppingCart.getVehicles()
                .stream().filter(i -> i.getRegistration().equals(registration))
                .collect(Collectors.toList()).size() > 0)
            throw new VehicleAlreadyInShoppingCartException(registration, username);
        shoppingCart.getVehicles().add(vehicle);
        this.shoppingCartRepository.save(shoppingCart);
        return Optional.of(shoppingCart);
    }

    @Override
    public void deleteVehicleToShoppingCart(String username, String registration) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username).get();
        Vehicle vehicle = this.vehicleRepository.findById(registration)
                .orElseThrow(() -> new VehicleNotFoundException(registration));
        if (shoppingCart.getVehicles().contains(vehicle)) {
            shoppingCart.getVehicles().remove(vehicle);
            this.shoppingCartRepository.save(shoppingCart);
        }
    }
}
