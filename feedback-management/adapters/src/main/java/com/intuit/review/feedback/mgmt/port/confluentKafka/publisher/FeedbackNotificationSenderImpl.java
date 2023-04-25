package com.intuit.review.feedback.mgmt.port.confluentKafka.publisher;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import com.intuit.review.feedback.mgmt.domain.exception.EventPublishException;
import com.intuit.review.feedback.mgmt.port.avro.FeedbackNotification;
import com.intuit.review.feedback.mgmt.port.confluentKafka.configuration.ConfluentKafkaConfigurationOptionsProvider;
import com.intuit.review.feedback.mgmt.service.port.kafka.MessageSender;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.internals.ProducerFactory;

@Slf4j
public class FeedbackNotificationSenderImpl implements MessageSender<String, FeedbackNotification> {

	private ConfluentKafkaConfigurationOptionsProvider optionsProvider;
	private KafkaSender<String, FeedbackNotification> kafkaSender;

	public FeedbackNotificationSenderImpl(ConfluentKafkaConfigurationOptionsProvider optionsProvider,
																				ProducerFactory producerFactory) {
		this.optionsProvider = optionsProvider;
		this.kafkaSender = KafkaSender
			.create(producerFactory, optionsProvider.senderOptions(MessageSender.NOTIFICATION_REQUEST_KEY));
	}

	@Override
	public Mono<Void> sendMessage(String key, Mono<FeedbackNotification> message, Map<String, String> headers) {
		return kafkaSender.createOutbound()
			.send(message
				.doOnNext(notification -> log.info("Sending feedback request for key={}, objectId={}, objectType={}.", key, notification.getObjectId(), notification.getObjectType()))
				.map(value -> optionsProvider
					.producerRecord(value, key, headers, MessageSender.NOTIFICATION_REQUEST_KEY)))
			.then()
			.doOnSuccess(
				success -> log.info("Successfully sent feedback notification request for key={}", key))
			.onErrorMap(
				throwable -> new EventPublishException("Failed to submit polling command request for key=".concat(key),
					throwable))
			.doOnError(
				throwable -> log.error("Sending polling command request failed for request key={}", key, throwable));
	}

}
