package com.project.uber.services.Impl;

import com.project.uber.dto.DriverDTO;
import com.project.uber.dto.SignupDTO;
import com.project.uber.dto.UserDTO;
import com.project.uber.entities.User;
import com.project.uber.entities.enums.Role;
import com.project.uber.exceptions.RuntimeConflictException;
import com.project.uber.repositories.UserRepository;
import com.project.uber.services.AuthService;
import com.project.uber.services.RiderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final RiderService riderService;



    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    @Transactional
    public UserDTO signup(SignupDTO signupDTO) {
        User user = userRepository
                .findByEmail(signupDTO.getEmail())
                .orElse(null);
        if(user!=null){
            throw new RuntimeConflictException("User Already exists");
        }
        User mappedUser = mapper.map(signupDTO, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappedUser);
        // create user related entities
        // rider
        riderService.createNewRider(savedUser);
        // TODO add wallet related service



        return mapper.map(savedUser, UserDTO.class);
    }

    @Override
    public DriverDTO onBoardDTO(Long userId) {
        return null;
    }
}
