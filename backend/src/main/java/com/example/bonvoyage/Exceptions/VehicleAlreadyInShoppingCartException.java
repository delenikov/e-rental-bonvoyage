package com.example.bonvoyage.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class VehicleAlreadyInShoppingCartException extends RuntimeException {

    public VehicleAlreadyInShoppingCartException(String registration, String username) {
        super(String.format("Vehicle with registration: %s already exists in shopping cart for user with username %s", registration, username));
    }
}
