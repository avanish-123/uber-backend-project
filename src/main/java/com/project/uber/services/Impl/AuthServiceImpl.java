package com.project.uber.services.Impl;

import com.project.uber.dto.DriverDTO;
import com.project.uber.dto.SignupDTO;
import com.project.uber.dto.UserDTO;
import com.project.uber.entities.Driver;
import com.project.uber.entities.User;
import com.project.uber.entities.enums.Role;
import com.project.uber.exceptions.ResourceNotFoundException;
import com.project.uber.exceptions.RuntimeConflictException;
import com.project.uber.repositories.UserRepository;
import com.project.uber.services.AuthService;
import com.project.uber.services.DriverService;
import com.project.uber.services.RiderService;
import com.project.uber.services.WalletService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;



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
        User mappedUser = modelMapper.map(signupDTO, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappedUser);
        // create user related entities
        // rider
        riderService.createNewRider(savedUser);

        walletService.createNewWallet(savedUser);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public DriverDTO onBoardNewDriver(Long userId, String vehicleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with user Id: "+ userId));

        if(user.getRoles().contains(Role.DRIVER)){
            throw new RuntimeConflictException("User is already a driver");
        }
        Driver driver = Driver.builder()
                .user(user)
                .rating(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();
        user.getRoles().add(Role.DRIVER);
        userRepository.save(user);
        Driver savedDriver = driverService.createNewDriver(driver);
        return modelMapper.map(savedDriver, DriverDTO.class);
    }
}
