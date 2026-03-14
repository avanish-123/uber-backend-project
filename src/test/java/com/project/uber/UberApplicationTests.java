package com.project.uber;

import com.project.uber.services.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UberApplicationTests {
	@Autowired
	private EmailSenderService emailSenderService;

	@Test
	void contextLoads() {
		emailSenderService.sendEmail(new String[]{"avanishchandrapandey022@gmail.com", "hexagon.jpeg@gmail.com"}, "test email", "test email body");
	}

}
