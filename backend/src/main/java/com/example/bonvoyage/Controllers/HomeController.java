package com.example.bonvoyage.Controllers;

import com.example.bonvoyage.Enumeration.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping("/roles")
    private List<Role> listAllUserRoles() {
        List<Role> roles = new ArrayList<Role>(EnumSet.allOf(Role.class));
        return roles;
    }
}
