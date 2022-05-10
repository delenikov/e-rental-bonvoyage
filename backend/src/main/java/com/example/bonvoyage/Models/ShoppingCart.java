package com.example.bonvoyage.Models;

import com.example.bonvoyage.Enumeration.ShoppingCartStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @JsonIgnore
    @OneToOne
    private User user;

    @JsonIgnore
    @ManyToMany
    private List<Vehicle> vehicles;

    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    public ShoppingCart() {
    }

    public ShoppingCart(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.vehicles = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;
    }

}
