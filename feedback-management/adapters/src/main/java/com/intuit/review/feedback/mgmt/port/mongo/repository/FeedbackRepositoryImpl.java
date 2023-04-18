package com.intuit.review.feedback.mgmt.port.mongo.repository;

import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.PageRequest;

import com.intuit.review.feedback.mgmt.domain.bo.common.ErrorBo;
import com.intuit.review.feedback.mgmt.domain.bo.common.ErrorDetailBo;
import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackBo;
import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackStatus;
import com.intuit.review.feedback.mgmt.domain.exception.ErrorConstants;
import com.intuit.review.feedback.mgmt.domain.exception.ServiceException;
import com.intuit.review.feedback.mgmt.port.mongo.mapper.FeedbackRequestBoEntityMapper;
import com.intuit.review.feedback.mgmt.service.port.mongo.FeedbackRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class FeedbackRepositoryImpl implements FeedbackRepository {

	private final FeedbackMongoRepository repository;
	private final FeedbackRequestBoEntityMapper mapper;

	@Override
	public Mono<FeedbackBo> initializeFeedback(FeedbackBo requestBo) {
		log.info("Saving feedback with subjectId={} to database", requestBo.getSubject().getId());
		return Mono.just(mapper.mapBoToEntity(requestBo))
			.doOnNext(feedbackRequest -> log.info("Feedback is {}", feedbackRequest.getSubject()))
			.flatMap(repository::insert)
			.map(mapper::mapEntityToBo);
	}

	@Override
	public Mono<FeedbackBo> finalizeFeedback(FeedbackBo requestBo) {
		log.info("Saving feedback with subjectId={} to database", requestBo.getSubject().getId());
		return Mono.just(mapper.mapBoToEntity(requestBo))
			.map(feedback -> {
				feedback.setStatus(FeedbackStatus.FINALIZED);
				return feedback;
			})
			.flatMap(repository::save)
			.map(mapper::mapEntityToBo);
	}

	@Override
	public Mono<FeedbackBo> getFeedback(String id) {
		log.info("Fetching feedback request with id={} from database", id);
		return repository.findById(id)
			.map(mapper::mapEntityToBo);
	}

	@Override
	public Flux<FeedbackBo> getInitializedFeedbacksForActor(String actorId, int page, int size) {
		log.info("Getting all initialized feedbacks for actorId={}", actorId);
		return repository.findAllByStatusAndActor_Id(FeedbackStatus.INITIALIZED, actorId, PageRequest.of(page - 1, size))
			.doOnError(throwable -> {
				log.error("Cannot get initialized feedbacks", throwable);
				ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR).status(500)
					.message("Cannot get initialized feedbacks")
					.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR)
						.message(throwable.getMessage()).build()))
					.build();
				throw new ServiceException(errorBo);
			})
			.map(mapper::mapEntityToBo);
	}

	@Override
	public Mono<Long> getCountOfInitializedFeedbacksForActor(String actorId) {
		return repository.countByActor_Id(actorId)
			.doOnError(throwable -> {
				log.error("Exception while counting intializedFeedbacksForActors", throwable);
				ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR).status(500)
					.message("Exception while counting intializedFeedbacksForActors")
					.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR)
						.message(throwable.getMessage()).build()))
					.build();
				throw new ServiceException(errorBo);
			});
	}

	@Override
	public Flux<FeedbackBo> getMyFeedbacks(String subjectId, int page, int size) {
		log.info("Getting all feedbacks for subjectId={}", subjectId);
		return repository.findAllByStatusAndSubject_Id(FeedbackStatus.FINALIZED, subjectId, PageRequest.of(page - 1, size))
			.doOnError(throwable -> {
				log.error("Cannot get feedbacks", throwable);
				ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR).status(500)
					.message("Cannot get feedbacks")
					.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR)
						.message(throwable.getMessage()).build()))
					.build();
				throw new ServiceException(errorBo);
			})
			.map(mapper::mapEntityToBo);
	}

	@Override
	public Mono<Long> getCountOfMyFeedbacks(String subjectId) {
		return repository.countByStatusAndSubject_Id(FeedbackStatus.FINALIZED, subjectId)
			.doOnError(throwable -> {
				log.error("Exception while counting getCountOfMyFeedbacks", throwable);
				ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR).status(500)
					.message("Exception while counting getCountOfMyFeedbacks")
					.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR)
						.message(throwable.getMessage()).build()))
					.build();
				throw new ServiceException(errorBo);
			});
	}

	@Override
	public Flux<FeedbackBo> getDirectReportingFeedbacks(List<String> subjectIds, int page, int size) {
		log.info("Getting all feedbacks for subjectId={}", subjectIds);
		return repository.findAllByStatusIsAndSubject_IdIn(FeedbackStatus.FINALIZED, subjectIds, PageRequest.of(page - 1, size))
			.doOnError(throwable -> {
				log.error("Cannot get feedbacks", throwable);
				ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR).status(500)
					.message("Cannot get feedbacks")
					.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR)
						.message(throwable.getMessage()).build()))
					.build();
				throw new ServiceException(errorBo);
			})
			.map(mapper::mapEntityToBo);
	}

	@Override
	public Mono<Long> getCountOfDirectReportingFeedbacks(List<String> subjectIds) {
		return repository.countByStatusAndSubject_IdIn(FeedbackStatus.FINALIZED, subjectIds)
			.doOnError(throwable -> {
				log.error("Exception while counting getCountOfDirectReportingFeedbacks", throwable);
				ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR).status(500)
					.message("Exception while counting getCountOfDirectReportingFeedbacks")
					.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR)
						.message(throwable.getMessage()).build()))
					.build();
				throw new ServiceException(errorBo);
			});
	}
}
