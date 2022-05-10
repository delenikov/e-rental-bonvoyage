package com.example.bonvoyage.Models.DTOs;

import com.example.bonvoyage.Enumeration.Role;
import com.example.bonvoyage.Models.User;
import lombok.Data;

@Data
public class UserDetailsDto {
    private String username;
    private Role role;

    public static UserDetailsDto of(User user) {
        UserDetailsDto details = new UserDetailsDto();
        details.username = user.getUsername();
        details.role = user.getRole();
        return details;
    }
}
