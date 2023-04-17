package com.intuit.review.feedback.mgmt.service.port.mongo;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackRequestBo;
import reactor.core.publisher.Mono;

public interface FeedbackRequestRepository {

	Mono<FeedbackRequestBo> createFeedbackRequest(FeedbackRequestBo requestBo);
}
