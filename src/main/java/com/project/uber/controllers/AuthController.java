package com.project.uber.controllers;

import com.project.uber.dto.SignupDTO;
import com.project.uber.dto.UserDTO;
import com.project.uber.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/signup")
    public UserDTO signup(@RequestBody SignupDTO signupDTO){
        return authService.signup(signupDTO);
    }
}
