package com.harun.virtualInvestmentPlatform.service;

import com.harun.virtualInvestmentPlatform.dao.UserRepository;
import com.harun.virtualInvestmentPlatform.dto.request.LoginRequest;
import com.harun.virtualInvestmentPlatform.dto.request.RegisterRequest;
import com.harun.virtualInvestmentPlatform.model.User;
import com.harun.virtualInvestmentPlatform.security.JwtUtil;
import com.harun.virtualInvestmentPlatform.util.PasswordHashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private UserRepository userRepository;
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserRepository userRepository , JwtUtil jwtUtil, AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public User registerUser(RegisterRequest registerRequest) {
        Optional<User> userOptional = userRepository.findByEmail(registerRequest.getEmail());
        if (userOptional.isPresent()) {
            return null;
        }

        User user = new User();
        user.setName(registerRequest.getName());
        user.setSurname(registerRequest.getSurname());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(PasswordHashUtil.encodePassword(registerRequest.getPassword()));
        return userRepository.save(user);
    }

    public String login(LoginRequest loginRequest) {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        if (!authentication.isAuthenticated() || authentication.getPrincipal() == null)
            return null;

        // If authentication was successful, proceed to create JWT
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return jwt;
    }
}
