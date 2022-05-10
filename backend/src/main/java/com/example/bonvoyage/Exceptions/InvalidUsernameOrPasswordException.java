package com.example.bonvoyage.Exceptions;

public class InvalidUsernameOrPasswordException extends RuntimeException {
    public InvalidUsernameOrPasswordException() {
        super("Invalid Username or password.");
    }
}
