package com.intuit.review.feedback.mgmt.service.feedback;

import java.time.Instant;
import java.util.Arrays;

import lombok.RequiredArgsConstructor;

import com.intuit.review.feedback.mgmt.domain.bo.common.CommonConstant;
import com.intuit.review.feedback.mgmt.domain.bo.common.ErrorBo;
import com.intuit.review.feedback.mgmt.domain.bo.common.ErrorDetailBo;
import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackBo;
import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackLightBo;
import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackStatus;
import com.intuit.review.feedback.mgmt.domain.exception.ErrorConstants;
import com.intuit.review.feedback.mgmt.domain.exception.FeedbackNotFoundException;
import com.intuit.review.feedback.mgmt.domain.exception.PaginationException;
import com.intuit.review.feedback.mgmt.domain.exception.ServiceException;
import com.intuit.review.feedback.mgmt.service.port.http.UserService;
import com.intuit.review.feedback.mgmt.service.port.mongo.FeedbackRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FeedbackUC {

	private final FeedbackRepository feedbackRepository;
	private final UserService userService;

	/**
	 * Initializes a feedback with bare minimum inforamtion into the database.
	 * Calls the user service to enrich user information.
	 * @param lightBo
	 * @return
	 */
	public Mono<FeedbackBo> initializeFeedback(FeedbackLightBo lightBo) {

		// orchestration saving the create feedback request to the database
		// get the requestor, actor and subject from the user service using the user service client
		FeedbackBo requestBo = FeedbackBo.builder()
			.status(FeedbackStatus.INITIALIZED)
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
					rb1.setRequestor(requestor);
					return rb1;
				})
			)
			// save the entity to database
			.flatMap(feedbackRepository::initializeFeedback);
	}

	public Mono<FeedbackBo> finalizeFeedback(String id, FeedbackLightBo lightBo) {
		// find the feedback in the database
		return feedbackRepository.getFeedback(id)
			// Only initialized feedbacks can be finalized
			.filter(feedbackBo -> feedbackBo.getStatus() == FeedbackStatus.INITIALIZED)
			.switchIfEmpty(Mono.error(new FeedbackNotFoundException(ErrorBo.builder().code(ErrorConstants.APPLICATION_NOT_FOUND).status(404)
				.message("Feedback with id=".concat(id).concat(" does not exist")).build()
			)))
			// get the data
			.map(feedbackBo -> {
				feedbackBo.setData(lightBo.getData());
				return feedbackBo;
			})
			.flatMap(feedbackRepository::finalizeFeedback)
			.doOnError(error -> {
				if (!(error instanceof FeedbackNotFoundException)) {
					ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR).status(500)
						.message("Error getting feedback")
						.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR)
							.message(error.getMessage()).build()))
						.build();
					throw new ServiceException(errorBo);
				}
			});
	}

	/**
	 * get a feedback from database using id
	 * @param id
	 * @return
	 */
	public Mono<FeedbackBo> getFeedbackRequestById(String id) {
		// find the feedback in the database
		return feedbackRepository.getFeedback(id)
			// if not found return exception
			.switchIfEmpty(Mono.error(new FeedbackNotFoundException(ErrorBo.builder().code(ErrorConstants.APPLICATION_NOT_FOUND).status(404)
				.message("Feedback with id=".concat(id).concat(" does not exist")).build()
			)))
			.doOnError(error -> {
				if (!(error instanceof FeedbackNotFoundException)) {
					ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR).status(500)
						.message("Error getting feedback")
						.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR)
							.message(error.getMessage()).build()))
						.build();
					throw new ServiceException(errorBo);
				}
			});
	}

	public Flux<FeedbackBo> getInitializedFeedbacksForActor(String actorId, int page, int size) {
		if (page <= 0) {
			return Flux.error(new PaginationException(ErrorBo.builder().code(ErrorConstants.PAGINATION_ERROR).status(500)
				.message("Invalid Page number").build()));
		}

		return feedbackRepository
			.getInitializedFeedbacksForActor(actorId, page, size)
			.doOnError(error -> {
				ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR).status(500)
					.message("Error while fetching initialized feedbacks for actor ".concat(actorId))
					.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR)
						.message(error.getMessage())
						.build())).build();
				throw new ServiceException(errorBo);
			});
	}

	public Mono<Long> getCountOfInitializedFeedbacksForActor(String actorId) {
		return feedbackRepository
			.getCountOfInitializedFeedbacksForActor(actorId)
			.doOnError(error -> {
				ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR).status(500)
					.message("Error while fetching number of feedbacks")
					.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR)
						.message(error.getMessage())
						.build())).build();
				throw new ServiceException(errorBo);
			});
	}

	public Flux<FeedbackBo> getMyFeedbacks(String subjectId, int page, int size) {
		if (page <= 0) {
			return Flux.error(new PaginationException(ErrorBo.builder().code(ErrorConstants.PAGINATION_ERROR).status(500)
				.message("Invalid Page number").build()));
		}

		return feedbackRepository
			.getMyFeedbacks(subjectId, page, size)
			.doOnError(error -> {
				ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR).status(500)
					.message("Error while fetching initialized feedbacks for subject ".concat(subjectId))
					.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR)
						.message(error.getMessage())
						.build())).build();
				throw new ServiceException(errorBo);
			});
	}

	public Mono<Long> getCountOfMyFeedbacks(String subjectId) {
		return feedbackRepository
			.getCountOfMyFeedbacks(subjectId)
			.doOnError(error -> {
				ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR).status(500)
					.message("Error while fetching number of feedbacks")
					.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR)
						.message(error.getMessage())
						.build())).build();
				throw new ServiceException(errorBo);
			});
	}

	public Flux<FeedbackBo> getDirectReportingFeedback(String managerId, int page, int size) {
		if (page <= 0) {
			return Flux.error(new PaginationException(ErrorBo.builder().code(ErrorConstants.PAGINATION_ERROR).status(500)
				.message("Invalid Page number").build()));
		}

		return userService.getDirectReporting(managerId)
			.map(feedbackUserBo -> feedbackUserBo.getId())
			.collectList()
			.flatMapMany(strings -> {
				return feedbackRepository
					.getDirectReportingFeedbacks(strings, page, size)
					.doOnError(error -> {
						ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR).status(500)
							.message("Error while fetching initialized feedbacks for subject ids")
							.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR)
								.message(error.getMessage())
								.build())).build();
						throw new ServiceException(errorBo);
					});
			});
	}

	public Mono<Long> getCountOfDirectReportingFeedback(String managerId) {
		return userService.getDirectReporting(managerId)
			.map(feedbackUserBo -> feedbackUserBo.getId())
			.collectList()
			.flatMap(subjectIds -> {
				return feedbackRepository
					.getCountOfDirectReportingFeedbacks(subjectIds)
					.doOnError(error -> {
						ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR).status(500)
							.message("Error while fetching number of feedbacks")
							.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR)
								.message(error.getMessage())
								.build())).build();
						throw new ServiceException(errorBo);
					});
			});
	}

	private long getValidUntilTime() {
		return Instant.now().toEpochMilli() + CommonConstant.EXPIRY;
	}
}
