package com.intuit.review.user.mgmt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intuit.review.user.mgmt.port.mongo.mapper.UserBoEntityMapper;
import com.intuit.review.user.mgmt.port.mongo.repository.UserMongoRepository;
import com.intuit.review.user.mgmt.port.mongo.repository.UserRepositoryImpl;
import com.intuit.review.user.mgmt.service.port.mongo.UserRepository;

@Configuration
public class AdaptersConfiguration {

	// mappers

	// handlers

	// repositories
	@Bean
	public UserRepository userRepository(UserMongoRepository mongoRepository, UserBoEntityMapper mapper) {
		return new UserRepositoryImpl(mongoRepository, mapper);
	}
}
