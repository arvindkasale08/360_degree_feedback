package com.intuit.review.feedback.mgmt.service.port.http;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackUserBo;
import reactor.core.publisher.Mono;

public interface UserService {

	Mono<FeedbackUserBo> getUser(String id);
}
