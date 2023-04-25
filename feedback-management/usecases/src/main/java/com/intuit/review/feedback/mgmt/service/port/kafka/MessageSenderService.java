package com.intuit.review.feedback.mgmt.service.port.kafka;

import java.util.Map;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackBo;
import reactor.core.publisher.Mono;

public interface MessageSenderService {

	Mono<Void> publishFeedbackNotification(String key, FeedbackBo message, Map<String, String> headers);
}
