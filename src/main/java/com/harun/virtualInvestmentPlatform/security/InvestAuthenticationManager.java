package com.harun.virtualInvestmentPlatform.security;

import com.harun.virtualInvestmentPlatform.dao.UserRepository;
import com.harun.virtualInvestmentPlatform.model.User;
import com.harun.virtualInvestmentPlatform.util.PasswordHashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InvestAuthenticationManager implements AuthenticationManager {
    private UserRepository userRepository;

    @Autowired
    public InvestAuthenticationManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        InvestAuthentication auth = new InvestAuthentication();
        String email = String.valueOf(authentication.getPrincipal());
        Optional<User> optionalUser = userRepository.findByEmail(email);
        optionalUser.ifPresent(user -> {
            if (PasswordHashUtil.matches((String) authentication.getCredentials(),user.getPassword())) {
                auth.setAuthenticated(true);
                auth.setUserDetails(new InvestUserDetails(user));
            }
        });

        return auth;
    }
}
