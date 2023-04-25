package com.intuit.review.feedback.mgmt.port.confluentKafka.publisher;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackBo;
import com.intuit.review.feedback.mgmt.port.confluentKafka.mapper.FeedbackBoAvroMapper;
import com.intuit.review.feedback.mgmt.service.port.kafka.MessageSenderService;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
public class MessageSenderServiceImpl implements MessageSenderService {

	private FeedbackNotificationSenderImpl feedbackNotificationSender;
	private FeedbackBoAvroMapper feedbackBoAvroMapper;

	@Override
	public Mono<Void> publishFeedbackNotification(String key, FeedbackBo message, Map<String, String> headers) {
		return Mono.just(feedbackBoAvroMapper.mapBoToEvent(message))
			.transformDeferred(messageMono -> feedbackNotificationSender.sendMessage(key, messageMono, headers));
	}
}
