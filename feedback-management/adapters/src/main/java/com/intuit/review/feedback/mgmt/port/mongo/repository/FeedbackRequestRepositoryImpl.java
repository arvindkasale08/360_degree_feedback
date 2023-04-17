package com.intuit.review.feedback.mgmt.port.mongo.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackRequestBo;
import com.intuit.review.feedback.mgmt.port.mongo.mapper.FeedbackRequestBoEntityMapper;
import com.intuit.review.feedback.mgmt.service.port.mongo.FeedbackRequestRepository;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class FeedbackRequestRepositoryImpl implements FeedbackRequestRepository {

	private final FeedbackRequestMongoRepository repository;
	private final FeedbackRequestBoEntityMapper mapper;

	@Override
	public Mono<FeedbackRequestBo> createFeedbackRequest(FeedbackRequestBo requestBo) {
		log.info("Saving feedback request with subjectId={} to database", requestBo.getSubject().getId());
		return Mono.just(mapper.mapBoToEntity(requestBo))
			.doOnNext(feedbackRequest -> log.info("Feedback request is {}", feedbackRequest.getSubject()))
			.flatMap(repository::insert)
			.map(mapper::mapEntityToBo);
	}

	@Override
	public Mono<FeedbackRequestBo> getFeedbackRequest(String id) {
		log.info("Fetching feedback request with id={} from database", id);
		return repository.findById(id)
			.map(mapper::mapEntityToBo);
	}
}
