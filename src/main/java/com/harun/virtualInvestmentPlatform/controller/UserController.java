package com.harun.virtualInvestmentPlatform.controller;

import com.harun.virtualInvestmentPlatform.dto.UserDto;
import com.harun.virtualInvestmentPlatform.dto.request.GetUserRequest;
import com.harun.virtualInvestmentPlatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String jwtToken) {
        System.out.println(jwtToken);
        System.out.println("Geldi");
        UserDto userDto = userService.getUser(jwtToken);
        return userDto != null ? ResponseEntity.ok(userDto) : ResponseEntity.badRequest().build();
    }
}
