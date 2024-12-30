package com.maxlogic.tutorial.spring_security_tutorial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxlogic.tutorial.spring_security_tutorial.auth.jwt.JwtTokenProvider;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserDetailsManager userDetailsManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("User login request: {}", loginRequest.username);
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken
                .unauthenticated(loginRequest.username(), loginRequest.password());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        if (authenticationResponse.isAuthenticated()) {
            log.info("User login success: {}", loginRequest.username);
            return ResponseEntity.ok().body(
                    new LoginResponse(loginRequest.username, jwtTokenProvider.generateToken(authenticationRequest)));
        }
        log.info("User login failed: {}", loginRequest.username);
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/admin/register-user")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        log.info("Received request to register user: {}", user.username());
        if (userDetailsManager.userExists(user.username())) {
            log.info("User already registered: {}", user.username());
            return ResponseEntity.badRequest().body("User already exists");
        }
        userDetailsManager.createUser(
                org.springframework.security.core.userdetails.User.withUsername(user.username())
                        .password(passwordEncoder.encode(user.password()))
                        .roles(user.roles())
                        .authorities(user.permissions() != null ? user.permissions() : new String[0])
                        .build());
        log.info("User registered successfully: {}", user.username());
        return ResponseEntity.ok().body("User created successfully");
    }

    public record LoginRequest(@NotEmpty(message = "Username is mandatory") String username,
            @NotEmpty(message = "Password is mandatory") String password) {
    }

    public record LoginResponse(String username, String token) {
    }

    public record User(
            @NotEmpty(message = "Username is mandatory") String username,
            @NotEmpty(message = "Password is mandatory") String password,
            @NotEmpty(message = "Atleast one role should be assigned to user") String[] roles,
            String[] permissions) {
    }
}
