package com.example.bonvoyage.Repository;

import com.example.bonvoyage.Enumeration.ShoppingCartStatus;
import com.example.bonvoyage.Models.ShoppingCart;
import com.example.bonvoyage.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);
}
