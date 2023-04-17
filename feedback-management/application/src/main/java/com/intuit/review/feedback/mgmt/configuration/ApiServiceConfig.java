package com.intuit.review.feedback.mgmt.configuration;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intuit.review.feedback.mgmt.port.http.client.UserServiceImpl;
import com.intuit.review.feedback.mgmt.service.port.http.UserService;

@Slf4j
@Configuration
public class ApiServiceConfig {


	// Client Configurations
	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
}
