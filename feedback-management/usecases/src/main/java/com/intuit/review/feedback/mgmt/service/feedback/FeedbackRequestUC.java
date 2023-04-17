package com.intuit.review.feedback.mgmt.service.feedback;

import java.time.Instant;

import lombok.RequiredArgsConstructor;

import com.intuit.review.feedback.mgmt.domain.bo.common.CommonConstant;
import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackRequestBo;
import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackRequestLightBo;
import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackRequestStatus;
import com.intuit.review.feedback.mgmt.service.port.http.UserService;
import com.intuit.review.feedback.mgmt.service.port.mongo.FeedbackRequestRepository;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FeedbackRequestUC {

	private final FeedbackRequestRepository feedbackRequestRepository;
	private final UserService userService;

	public Mono<FeedbackRequestBo> createFeedbackRequest(FeedbackRequestLightBo lightBo) {

		// orchestration saving the create feedback request to the database
		// get the requestor, actor and subject from the user service using the user service client
		FeedbackRequestBo requestBo = FeedbackRequestBo.builder()
			.status(FeedbackRequestStatus.INITIATED)
			.validTill(getValidUntilTime())
			.build();

		return Mono.just(requestBo)
			// get actor information from user service
			.flatMap(rb1 -> userService.getUser(lightBo.getActorId()).map(actor -> {
				rb1.setActor(actor);
				return rb1;
			})
			)
			// get subject information from user service
			.flatMap(rb1 -> userService.getUser(lightBo.getSubjectId()).map(subject -> {
				rb1.setSubject(subject);
				return rb1;
			})
			)
			// get requestor information from user service
			.flatMap(rb1 -> userService.getUser(lightBo.getRequestorId()).map(requestor -> {
					rb1.setSubject(requestor);
					return rb1;
				})
			)
			// save the entity to database
			.flatMap(feedbackRequestRepository::createFeedbackRequest);
	}

	private long getValidUntilTime() {
		return Instant.now().toEpochMilli() + CommonConstant.EXPIRY;
	}
}
