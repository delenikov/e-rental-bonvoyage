package com.example.bonvoyage.Service.Implementation;

import com.example.bonvoyage.Enumeration.Role;
import com.example.bonvoyage.Exceptions.InvalidUsernameOrPasswordException;
import com.example.bonvoyage.Exceptions.PasswordsDoNotMatchException;
import com.example.bonvoyage.Exceptions.UserNotFoundException;
import com.example.bonvoyage.Exceptions.UsernameAlreadyExistsException;
import com.example.bonvoyage.Models.DTOs.RegisterDto;
import com.example.bonvoyage.Models.DTOs.UserDetailsDto;
import com.example.bonvoyage.Models.User;
import com.example.bonvoyage.Repository.UserRepository;
import com.example.bonvoyage.Service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }
    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username, passwordEncoder.encode(password), name, surname, role);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> register(RegisterDto registerDto) {
        if (registerDto.getUsername()==null || registerDto.getUsername().isEmpty()  || registerDto.getPassword()==null || registerDto.getPassword().isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!registerDto.getPassword().equals(registerDto.getRepeatPassword()))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(registerDto.getUsername()).isPresent())
            throw new UsernameAlreadyExistsException(registerDto.getUsername());
        User user = new User(registerDto.getUsername(), passwordEncoder.encode(registerDto.getPassword()), registerDto.getName(), registerDto.getSurname(), registerDto.getRole());
        this.userRepository.save(user);
        return Optional.of(user);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(()-> new UserNotFoundException(username));
    }
}
