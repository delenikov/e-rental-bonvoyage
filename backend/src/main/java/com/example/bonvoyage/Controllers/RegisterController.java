package com.example.bonvoyage.Controllers;

import com.example.bonvoyage.Enumeration.Role;
import com.example.bonvoyage.Exceptions.PasswordsDoNotMatchException;
import com.example.bonvoyage.Models.DTOs.RegisterDto;
import com.example.bonvoyage.Models.DTOs.UserDetailsDto;
import com.example.bonvoyage.Models.DTOs.VehicleDto;
import com.example.bonvoyage.Models.User;
import com.example.bonvoyage.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> register(@RequestBody RegisterDto registerDto) {
        return this.userService.register(registerDto)
                .map(vehicle -> ResponseEntity.ok().body(vehicle))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
