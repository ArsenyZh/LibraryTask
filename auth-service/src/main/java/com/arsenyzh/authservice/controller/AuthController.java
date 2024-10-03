package com.arsenyzh.authservice.controller;

import com.arsenyzh.authservice.dto.UserDto;
import com.arsenyzh.authservice.entity.User;
import com.arsenyzh.authservice.mapper.UserMapper;
import com.arsenyzh.authservice.service.AuthenticationService;
import com.arsenyzh.authservice.service.JwtService;
import lombok.AllArgsConstructor;
import com.arsenyzh.authservice.response.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {
    private JwtService jwtService;
    private AuthenticationService authenticationService;
    private UserMapper userMapper;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody UserDto userDto) {
        User registeredUser = authenticationService.signUp(userMapper.convertUserDtoToUser(userDto));
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody UserDto userDto) {
        User authenticatedUser = authenticationService.authenticate(userMapper.convertUserDtoToUser(userDto));
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = authenticationService.getLoginResponse(jwtToken);
        return ResponseEntity.ok(loginResponse);
    }
}
