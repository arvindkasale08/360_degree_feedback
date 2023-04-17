package com.intuit.review.feedback.mgmt.port.mongo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intuit.review.feedback.mgmt.domain.bo.common.OrikaOffsetDateLongConverter;
import com.intuit.review.feedback.mgmt.domain.bo.common.OrikaOffsetDateStringConverter;
import com.intuit.review.feedback.mgmt.port.mongo.mapper.FeedbackRequestBoEntityMapper;
import com.intuit.review.feedback.mgmt.port.mongo.mapper.FeedbackUserBoEntityMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Configuration
public class MapperConfiguration {

	@Bean
	public MapperFactory mapperFactory() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.getConverterFactory().registerConverter(new OrikaOffsetDateLongConverter());
		mapperFactory.getConverterFactory().registerConverter(new OrikaOffsetDateStringConverter());
		return mapperFactory;
	}

	@Bean
	public MapperFacade mapperFacade(MapperFactory mapperFactory) {
		return mapperFactory.getMapperFacade();
	}

	@Bean
	public FeedbackRequestBoEntityMapper feedbackRequestBoEntityMapper(MapperFacade mapperFacade, MapperFactory mapperFactory, FeedbackUserBoEntityMapper feedbackUserBoEntityMapper) {
		FeedbackRequestBoEntityMapper mapper = new FeedbackRequestBoEntityMapper(mapperFacade, feedbackUserBoEntityMapper);
		mapper.configure(mapperFactory);
		return mapper;
	}

	@Bean
	public FeedbackUserBoEntityMapper feedbackUserBoEntityMapper(MapperFacade mapperFacade, MapperFactory mapperFactory) {
		FeedbackUserBoEntityMapper mapper = new FeedbackUserBoEntityMapper(mapperFacade);
		return mapper;
	}
}
