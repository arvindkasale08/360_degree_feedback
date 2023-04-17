package com.intuit.review.feedback.mgmt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intuit.review.feedback.mgmt.port.http.FeedbackRequestHandler;
import com.intuit.review.feedback.mgmt.port.http.mapper.FeedbackRequestRequestDtoLightBoMapper;
import com.intuit.review.feedback.mgmt.port.http.mapper.FeedbackRequestResponseDtoBoMapper;
import com.intuit.review.feedback.mgmt.port.mongo.mapper.FeedbackRequestBoEntityMapper;
import com.intuit.review.feedback.mgmt.port.mongo.repository.FeedbackRequestMongoRepository;
import com.intuit.review.feedback.mgmt.port.mongo.repository.FeedbackRequestRepositoryImpl;
import com.intuit.review.feedback.mgmt.service.feedback.FeedbackRequestUC;
import com.intuit.review.feedback.mgmt.service.port.mongo.FeedbackRequestRepository;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;

@Configuration
public class AdaptersConfiguration {

	// mappers
	@Bean
	public FeedbackRequestRequestDtoLightBoMapper feedbackRequestRequestDtoLightBoMapper(MapperFacade mapperFacade, MapperFactory mapperFactory) {
		FeedbackRequestRequestDtoLightBoMapper mapper = new FeedbackRequestRequestDtoLightBoMapper(mapperFacade);
		return mapper;
	}

	@Bean
	public FeedbackRequestResponseDtoBoMapper feedbackRequestResponseDtoBoMapper(MapperFacade mapperFacade, MapperFactory mapperFactory) {
		FeedbackRequestResponseDtoBoMapper mapper = new FeedbackRequestResponseDtoBoMapper(mapperFacade);
		return mapper;
	}

	// handlers
	@Bean
	public FeedbackRequestHandler feedbackRequestHandler(FeedbackRequestUC feedbackRequestUC, FeedbackRequestRequestDtoLightBoMapper lightBoMapper, FeedbackRequestResponseDtoBoMapper responseDtoBoMapper) {
		return new FeedbackRequestHandler(feedbackRequestUC, lightBoMapper, responseDtoBoMapper);
	}


	// repositories
	@Bean
	public FeedbackRequestRepository feedbackRequestRepository(FeedbackRequestMongoRepository mongoRepository, FeedbackRequestBoEntityMapper mapper) {
		return new FeedbackRequestRepositoryImpl(mongoRepository, mapper);
	}
}
