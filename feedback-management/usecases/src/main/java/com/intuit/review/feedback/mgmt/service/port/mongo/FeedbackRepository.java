package com.intuit.review.feedback.mgmt.service.port.mongo;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackBo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeedbackRepository {

	Mono<FeedbackBo> initializeFeedback(FeedbackBo requestBo);

	Mono<FeedbackBo> getFeedback(String id);

	Flux<FeedbackBo> getInitializedFeedbacksForActor(String actorId, int page, int size);

	Mono<Long> getCountOfInitializedFeedbacksForActor(String actorId);
}
