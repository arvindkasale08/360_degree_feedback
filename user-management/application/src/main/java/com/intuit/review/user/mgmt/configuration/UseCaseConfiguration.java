package com.intuit.review.user.mgmt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intuit.review.user.mgmt.service.account.UserUC;
import com.intuit.review.user.mgmt.service.port.mongo.UserRepository;

@Configuration
public class UseCaseConfiguration {

	@Bean
	public UserUC feedbackRequestUC(UserRepository userRepository) {
		return new UserUC(userRepository);
	}
}
