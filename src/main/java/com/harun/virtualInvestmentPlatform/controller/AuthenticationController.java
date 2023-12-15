package com.harun.virtualInvestmentPlatform.controller;

import com.harun.virtualInvestmentPlatform.dto.LoginRequest;
import com.harun.virtualInvestmentPlatform.dto.RegisterRequest;
import com.harun.virtualInvestmentPlatform.model.User;
import com.harun.virtualInvestmentPlatform.security.JwtUtil;
import com.harun.virtualInvestmentPlatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private UserService userService;


    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        User user = userService.registerUser(registerRequest);
        return user != null ? ResponseEntity.ok("User registered successfully") : ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws AuthenticationException {
        String jwt = userService.login(loginRequest);
        if (jwt == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(jwt);

    }
}
