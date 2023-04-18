package com.intuit.review.user.mgmt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intuit.review.user.mgmt.port.http.UserHandler;
import com.intuit.review.user.mgmt.port.http.mapper.UserResponseDtoBoMapper;
import com.intuit.review.user.mgmt.port.mongo.mapper.UserBoEntityMapper;
import com.intuit.review.user.mgmt.port.mongo.repository.UserMongoRepository;
import com.intuit.review.user.mgmt.port.mongo.repository.UserRepositoryImpl;
import com.intuit.review.user.mgmt.service.account.UserUC;
import com.intuit.review.user.mgmt.service.port.mongo.UserRepository;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;

@Configuration
public class AdaptersConfiguration {

	// mappers
	@Bean
	public UserResponseDtoBoMapper userResponseDtoBoMapper(MapperFacade mapperFacade, MapperFactory mapperFactory) {
		UserResponseDtoBoMapper mapper = new UserResponseDtoBoMapper(mapperFacade);
		return mapper;
	}

	// handlers
	@Bean
	public UserHandler userHandler(UserUC userUC, UserResponseDtoBoMapper mapper) {
		return new UserHandler(userUC, mapper);
	}

	// repositories
	@Bean
	public UserRepository userRepository(UserMongoRepository mongoRepository, UserBoEntityMapper mapper) {
		return new UserRepositoryImpl(mongoRepository, mapper);
	}
}
