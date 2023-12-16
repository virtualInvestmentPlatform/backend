package com.harun.virtualInvestmentPlatform.controller;

import com.harun.virtualInvestmentPlatform.dto.request.LoginRequest;
import com.harun.virtualInvestmentPlatform.dto.request.RegisterRequest;
import com.harun.virtualInvestmentPlatform.model.User;
import com.harun.virtualInvestmentPlatform.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private AuthService authService;

    @Autowired
    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        User user = authService.registerUser(registerRequest);
        return user != null ? ResponseEntity.ok("User registered successfully") : ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws AuthenticationException {
        String jwt = authService.login(loginRequest);
        if (jwt == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(jwt);
    }
}
