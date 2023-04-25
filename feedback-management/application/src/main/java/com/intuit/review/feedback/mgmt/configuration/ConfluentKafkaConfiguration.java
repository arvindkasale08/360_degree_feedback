package com.intuit.review.feedback.mgmt.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intuit.review.feedback.mgmt.port.confluentKafka.configuration.ConfluentKafkaConfigurationOptionsProvider;
import com.intuit.review.feedback.mgmt.port.confluentKafka.configuration.ConfluentKafkaProperties;
import com.intuit.review.feedback.mgmt.port.confluentKafka.mapper.FeedbackBoAvroMapper;
import com.intuit.review.feedback.mgmt.port.confluentKafka.publisher.FeedbackNotificationSenderImpl;
import com.intuit.review.feedback.mgmt.port.confluentKafka.publisher.MessageSenderServiceImpl;
import com.intuit.review.feedback.mgmt.service.port.kafka.MessageSenderService;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import reactor.kafka.sender.internals.ProducerFactory;

@Configuration
public class ConfluentKafkaConfiguration {

	//  Confluent kafka connfiguration
	@Bean
	@ConfigurationProperties("confluentkafka")
	public ConfluentKafkaProperties confluentKafkaProperties() {
		return new ConfluentKafkaProperties();
	}

	@Bean
	public ConfluentKafkaConfigurationOptionsProvider confluentKafkaConfigurationOptionsProvider(ConfluentKafkaProperties confluentKafkaProperties) {
		return new ConfluentKafkaConfigurationOptionsProvider(confluentKafkaProperties);
	}

	@Bean
	public FeedbackNotificationSenderImpl feedbackNotificationSender(ConfluentKafkaConfigurationOptionsProvider confluentKafkaConfigurationOptionsProvider, ProducerFactory producerFactory) {
		return new FeedbackNotificationSenderImpl(confluentKafkaConfigurationOptionsProvider, producerFactory);
	}

	@Bean
	public MessageSenderService messageSenderService(FeedbackNotificationSenderImpl feedbackNotificationSender, FeedbackBoAvroMapper feedbackBoAvroMapper) {
		return new MessageSenderServiceImpl(feedbackNotificationSender, feedbackBoAvroMapper);
	}

	@Bean
	public FeedbackBoAvroMapper feedbackBoAvroMapper(MapperFactory mapperFactory, MapperFacade mapperFacade) {
		FeedbackBoAvroMapper mapper = new FeedbackBoAvroMapper(mapperFacade);
		mapper.configure(mapperFactory);
		return mapper;
	}
}
