package com.project.uber.services.Impl;

import com.project.uber.dto.DriverDTO;
import com.project.uber.dto.SignupDTO;
import com.project.uber.dto.UserDTO;
import com.project.uber.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDTO signup(SignupDTO signupDTO) {
        return null;
    }

    @Override
    public DriverDTO onBoardDTO(Long userId) {
        return null;
    }
}
