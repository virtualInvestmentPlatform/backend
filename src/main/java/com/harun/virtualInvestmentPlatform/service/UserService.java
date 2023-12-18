package com.harun.virtualInvestmentPlatform.service;


import com.harun.virtualInvestmentPlatform.dao.UserRepository;
import com.harun.virtualInvestmentPlatform.dto.UserDto;
import com.harun.virtualInvestmentPlatform.dto.request.GetUserRequest;
import com.harun.virtualInvestmentPlatform.model.User;
import com.harun.virtualInvestmentPlatform.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    private UserRepository userRepository;
    private JwtUtil jwtUtil;

    @Autowired
    public UserService(UserRepository userRepository , JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public User getUser(String jwtToken) {
        String pureJwtToken = jwtUtil.removeBearer(jwtToken);
        String email = jwtUtil.extractEmail(pureJwtToken);
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (!optionalUser.isPresent()) {
            return null;
        }

        User user = optionalUser.get();
        return user;
    }

    public UserDto getUserDto(String jwtToken) {
        String pureJwtToken = jwtUtil.removeBearer(jwtToken);
        String email = jwtUtil.extractEmail(pureJwtToken);
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (!optionalUser.isPresent()) {
            return null;
        }

        User user = optionalUser.get();
        return new UserDto(user.getName(),user.getSurname(),user.getBalance());
    }

}
