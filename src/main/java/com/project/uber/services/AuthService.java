package com.project.uber.services;

import com.project.uber.dto.DriverDTO;
import com.project.uber.dto.SignupDTO;
import com.project.uber.dto.UserDTO;

public interface AuthService {
    String login(String email, String password);
    UserDTO signup(SignupDTO signupDTO);

    DriverDTO onBoardDTO(Long userId);
}
