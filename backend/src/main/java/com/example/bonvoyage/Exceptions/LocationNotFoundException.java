package com.example.bonvoyage.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(Integer locationId) {
        super(String.format("Location with id %d, is not found!", locationId));
    }
}
