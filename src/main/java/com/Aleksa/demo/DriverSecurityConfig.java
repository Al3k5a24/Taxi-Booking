package com.Aleksa.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DriverSecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoderDriver() {
		return new BCryptPasswordEncoder();
	}
}
