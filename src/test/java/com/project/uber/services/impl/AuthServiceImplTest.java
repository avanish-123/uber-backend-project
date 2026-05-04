package com.project.uber.services.impl;

import com.project.uber.TestContainerConfiguration;
import com.project.uber.dto.SignupDTO;
import com.project.uber.entities.User;
import com.project.uber.entities.enums.Role;
import com.project.uber.repositories.UserRepository;
import com.project.uber.security.JWTService;
import com.project.uber.services.AuthService;
import com.project.uber.services.Impl.AuthServiceImpl;
import com.project.uber.services.Impl.DriverServiceImpl;
import com.project.uber.services.Impl.RiderServiceImpl;
import com.project.uber.services.Impl.WalletServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JWTService jwtService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RiderServiceImpl riderService;

    @Mock
    private WalletServiceImpl walletService;

    @Mock
    private DriverServiceImpl driverService;

    @Spy
    private ModelMapper modelMapper;

    @Spy
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    private User user;
    private SignupDTO signupDTO;

    @BeforeEach
    void setup(){
        user = new User();
        user.setId(1L);
        user.setEmail("avanishpandey022@gmail.com");
        user.setPassword("password");
        user.setRoles(Set.of(Role.RIDER));

        signupDTO = new SignupDTO();
        signupDTO.setEmail("avanishpandey022@gmail.com");
        signupDTO.setPassword("password");
    }

    @Test
    void testLogin_whenSuccess(){
        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(user);
        when(jwtService.generateAccessToken(any(User.class))).thenReturn("accessToken");
        when(jwtService.generateRefreshToken(any(User.class))).thenReturn("refreshToken");

        String[] tokens = authService.login(user.getEmail(), user.getPassword());

        assertThat(tokens).hasSize(2);
        assertThat(tokens[0]).isEqualTo("accessToken");
        assertThat(tokens[1]).isEqualTo("refreshToken");
    }
}
