package com.intuit.review.feedback.mgmt.service.port.mongo;

import java.util.List;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackBo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeedbackRepository {

	Mono<FeedbackBo> initializeFeedback(FeedbackBo feedbackBo);

	Mono<FeedbackBo> getFeedback(String id);

	Mono<FeedbackBo> finalizeFeedback(FeedbackBo feedbackBo);

	Flux<FeedbackBo> getInitializedFeedbacksForActor(String actorId, int page, int size);

	Mono<Long> getCountOfInitializedFeedbacksForActor(String actorId);

	Flux<FeedbackBo> getMyFeedbacks(String subjectId, int page, int size);

	Mono<Long> getCountOfMyFeedbacks(String subjectId);

	Flux<FeedbackBo> getDirectReportingFeedbacks(List<String> subjectIds, int page, int size);

	Mono<Long> getCountOfDirectReportingFeedbacks(List<String> subjectIds);
}
