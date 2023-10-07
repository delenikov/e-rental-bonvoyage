package com.example.bonvoyage.Config;

import com.example.bonvoyage.Config.Filters.JWTAuthenticationFilter;
import com.example.bonvoyage.Config.Filters.JWTAuthorizationFilter;
import com.example.bonvoyage.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Profile("session")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;
  private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;
  private final UserService userService;

  public WebSecurityConfig(PasswordEncoder passwordEncoder,
                           CustomUsernamePasswordAuthenticationProvider authenticationProvider,
                           UserService userService) {
    this.passwordEncoder = passwordEncoder;
    this.authenticationProvider = authenticationProvider;
    this.userService = userService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.cors().and().csrf().disable()
      .authorizeRequests()
      .antMatchers("/home", "/vehicles", "/vehicles/**", "/roles", "/locations",
        "/locations/**", "/auth/**", "/cart", "/cart/**").permitAll()
      .antMatchers("/admin/**").hasRole("ADMIN")
      .anyRequest()
      .authenticated()
      .and()
      .formLogin()
        .loginPage("/login").permitAll()
        .failureUrl("/login?error=BadCredentials")
        .defaultSuccessUrl("/vehicles", true)
      .and()
      .logout()
        .logoutUrl("/logout")
        .clearAuthentication(true)
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .logoutSuccessUrl("/login")
      .and()
      . addFilter(this.authenticationFilter())
      . addFilter(this.authorizationFilter())
      . exceptionHandling().accessDeniedPage("/access_denied");
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("*"));
    configuration.setAllowedMethods(List.of("*"));
    configuration.setAllowedHeaders(List.of("*"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationProvider);
  }

  @Bean
  public JWTAuthorizationFilter authorizationFilter() throws Exception {
    return new JWTAuthorizationFilter(authenticationManager(), userService);
  }

  @Bean
  public JWTAuthenticationFilter authenticationFilter() throws Exception {
    return new JWTAuthenticationFilter(authenticationManager(), userService, passwordEncoder);
  }
}
