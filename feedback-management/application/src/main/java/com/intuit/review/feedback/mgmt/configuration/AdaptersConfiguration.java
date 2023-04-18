package com.intuit.review.feedback.mgmt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intuit.review.feedback.mgmt.port.http.FeedbackHandler;
import com.intuit.review.feedback.mgmt.port.http.mapper.FinalizeFeedbackRequestLightBoMapper;
import com.intuit.review.feedback.mgmt.port.http.mapper.InitializeFeedbackRequestLightBoMapper;
import com.intuit.review.feedback.mgmt.port.http.mapper.FeedbackResponseDtoBoMapper;
import com.intuit.review.feedback.mgmt.port.http.mapper.UserResponseDTOtoFeedbackUserBoMapper;
import com.intuit.review.feedback.mgmt.port.mongo.mapper.FeedbackRequestBoEntityMapper;
import com.intuit.review.feedback.mgmt.port.mongo.repository.FeedbackMongoRepository;
import com.intuit.review.feedback.mgmt.port.mongo.repository.FeedbackRepositoryImpl;
import com.intuit.review.feedback.mgmt.service.feedback.FeedbackUC;
import com.intuit.review.feedback.mgmt.service.port.mongo.FeedbackRepository;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;

@Configuration
public class AdaptersConfiguration {

	// mappers
	@Bean
	public InitializeFeedbackRequestLightBoMapper initializeFeedbackRequestDtoLightBoMapper(MapperFacade mapperFacade, MapperFactory mapperFactory) {
		InitializeFeedbackRequestLightBoMapper mapper = new InitializeFeedbackRequestLightBoMapper(mapperFacade);
		return mapper;
	}

	@Bean
	public FinalizeFeedbackRequestLightBoMapper finalizeFeedbackRequestDtoLightBoMapper(MapperFacade mapperFacade, MapperFactory mapperFactory) {
		FinalizeFeedbackRequestLightBoMapper mapper = new FinalizeFeedbackRequestLightBoMapper(mapperFacade);
		return mapper;
	}

	@Bean
	public FeedbackResponseDtoBoMapper feedbackResponseDtoBoMapper(MapperFacade mapperFacade, MapperFactory mapperFactory) {
		FeedbackResponseDtoBoMapper mapper = new FeedbackResponseDtoBoMapper(mapperFacade);
		return mapper;
	}

	@Bean
	public UserResponseDTOtoFeedbackUserBoMapper userResponseDTOtoFeedbackUserBoMapper(MapperFacade mapperFacade, MapperFactory mapperFactory) {
		UserResponseDTOtoFeedbackUserBoMapper mapper = new UserResponseDTOtoFeedbackUserBoMapper(mapperFacade);
		mapper.configure(mapperFactory);
		return mapper;
	}

	// handlers
	@Bean
	public FeedbackHandler feedbackRequestHandler(FeedbackUC feedbackUC, InitializeFeedbackRequestLightBoMapper lightBoMapper, FinalizeFeedbackRequestLightBoMapper finalizeFeedbackRequestLightBoMapper, FeedbackResponseDtoBoMapper responseDtoBoMapper) {
		return new FeedbackHandler(feedbackUC, lightBoMapper, finalizeFeedbackRequestLightBoMapper, responseDtoBoMapper);
	}


	// repositories
	@Bean
	public FeedbackRepository feedbackRequestRepository(FeedbackMongoRepository mongoRepository, FeedbackRequestBoEntityMapper mapper) {
		return new FeedbackRepositoryImpl(mongoRepository, mapper);
	}
}
