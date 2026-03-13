package com.project.uber.controllers;

import com.project.uber.dto.*;
import com.project.uber.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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
    @Secured("ROLE_ADMIN")
    public ResponseEntity<DriverDTO> onBoardNewDriver(@PathVariable Long userId, @RequestBody OnboardDriverDTO onboardDriverDTO){
        return new ResponseEntity<>(authService.onBoardNewDriver(userId, onboardDriverDTO.getVehicleId()), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO,
                                                  HttpServletRequest httpServletRequest,
                                                  HttpServletResponse httpServletResponse){
        String[] tokens = authService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        Cookie cookie = new Cookie("token", tokens[1]);
        cookie.setHttpOnly(false);
        httpServletResponse.addCookie(cookie);
        return ResponseEntity.ok(new LoginResponseDTO(tokens[0]));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(HttpServletRequest request){
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "token".equals(cookie.getName()))
                .findFirst()
                .map(cookie -> cookie.getValue())
                .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found inside the cookie"));
        String accessToken = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(new LoginResponseDTO(accessToken));
    }
}
