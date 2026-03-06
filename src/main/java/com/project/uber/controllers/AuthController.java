package com.project.uber.controllers;

import com.project.uber.dto.DriverDTO;
import com.project.uber.dto.OnboardDriverDTO;
import com.project.uber.dto.SignupDTO;
import com.project.uber.dto.UserDTO;
import com.project.uber.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody SignupDTO signupDTO){
        return ResponseEntity.ok(authService.signup(signupDTO));
    }

    @PostMapping("/onBoardNewDriver/{userId}")
    public ResponseEntity<DriverDTO> onBoardNewDriver(@PathVariable Long userId, @RequestBody OnboardDriverDTO onboardDriverDTO){
        return new ResponseEntity<>(authService.onBoardNewDriver(userId, onboardDriverDTO.getVehicleId()), HttpStatus.CREATED);
    }

}
