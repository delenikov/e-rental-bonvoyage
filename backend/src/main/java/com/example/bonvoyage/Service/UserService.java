package com.example.bonvoyage.Service;

import com.example.bonvoyage.Enumeration.Role;
import com.example.bonvoyage.Models.DTOs.RegisterDto;
import com.example.bonvoyage.Models.DTOs.UserDetailsDto;
import com.example.bonvoyage.Models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
    Optional<User> register(RegisterDto registerDto);
    User findByUsername(String username);
}
