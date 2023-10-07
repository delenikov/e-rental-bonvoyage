package com.example.bonvoyage.Controllers;

import com.example.bonvoyage.Config.Filters.JWTAuthenticationFilter;
import com.example.bonvoyage.Models.DTOs.RegisterDto;
import com.example.bonvoyage.Models.User;
import com.example.bonvoyage.Service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final JWTAuthenticationFilter filter;
  private final UserService userService;

  public AuthController(JWTAuthenticationFilter filter, UserService userService) {
    this.filter = filter;
    this.userService = userService;
  }

  @PostMapping("/login")
  public String doLogin(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
    Authentication auth = this.filter.attemptAuthentication(request, response);
    return this.filter.generateJwt(response, auth);
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody RegisterDto registerDto) {
    return this.userService.register(registerDto)
      .map(vehicle -> ResponseEntity.ok().body(vehicle))
      .orElseGet(() -> ResponseEntity.badRequest().build());
  }
}
