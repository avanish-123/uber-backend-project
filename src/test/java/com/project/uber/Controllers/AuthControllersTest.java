package com.project.uber.Controllers;

import com.project.uber.TestContainerConfiguration;
import com.project.uber.services.Impl.AuthServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestContainerConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthControllersTest {

    @InjectMocks
    private AuthServiceImpl authService;
    @Test
    void testSignup_whenUserIsNotPresent_thenThrowException(){
        System.out.println("Sample output");
    }
}
