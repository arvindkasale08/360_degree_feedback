package com.intuit.review.feedback.mgmt.port.mongo.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackStatus;
import com.intuit.review.feedback.mgmt.port.mongo.entity.feedback.Feedback;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FeedbackMongoRepository extends ReactiveMongoRepository<Feedback, String> {

	Flux<Feedback> findAllByStatusAndActor_Id(FeedbackStatus status, String actorId, Pageable pageable);

	Mono<Long> countByActor_Id(String actorId);

	Flux<Feedback> findAllByStatusAndSubject_Id(FeedbackStatus status, String subjectId, Pageable pageable);

	Mono<Long> countByStatusAndSubject_Id(FeedbackStatus status, String actorId);
}
