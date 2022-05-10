package com.example.bonvoyage.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String registration) {
        super(String.format("Vehicle with registration %s, is not found!", registration));
    }
}
