package com.example.bonvoyage.Controllers;

import com.example.bonvoyage.Config.Filters.JWTAuthenticationFilter;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/api/login")
public class LoginController {

    private final JWTAuthenticationFilter filter;

    public LoginController(JWTAuthenticationFilter filter) {
        this.filter = filter;
    }


    @PostMapping
    public String doLogin(HttpServletRequest request,
                          HttpServletResponse response) throws JsonProcessingException {
        Authentication auth = this.filter.attemptAuthentication(request, response);
        return this.filter.generateJwt(response, auth);

    }
}
