package com.example.bonvoyage.Models.DTOs;

import com.example.bonvoyage.Enumeration.Role;
import lombok.Data;

@Data
public class RegisterDto {
    String username;
    String password;
    String repeatPassword;
    String name;
    String surname;
    Role role;

    public RegisterDto(String username, String password, String repeatPassword, String name, String surname, Role role) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public RegisterDto() {

    }
}
