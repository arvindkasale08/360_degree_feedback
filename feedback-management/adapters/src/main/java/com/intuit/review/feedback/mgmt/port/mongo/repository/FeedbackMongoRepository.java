package com.intuit.review.feedback.mgmt.port.mongo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackStatus;
import com.intuit.review.feedback.mgmt.port.mongo.entity.feedback.Feedback;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FeedbackMongoRepository extends ReactiveMongoRepository<Feedback, String> {

	Flux<Feedback> findAllByStatusAndActor_IdOrderByUpdatedDateDesc(FeedbackStatus status, String actorId, Pageable pageable);

	Mono<Long> countByActor_Id(String actorId);

	Flux<Feedback> findAllByStatusAndSubject_IdOrderByUpdatedDateDesc(FeedbackStatus status, String subjectId, Pageable pageable);

	Mono<Long> countByStatusAndSubject_Id(FeedbackStatus status, String subjectId);

	/*
	@Query(value = "{'status': ?0, 'subject.id': {$in: ?1}", sort = "{'id': -1}")
	Flux<Feedback> findAllForSubjectIdsByStatus(FeedbackStatus status, List<String> subjectId, Pageable pageable);
	*/

	Flux<Feedback> findAllByStatusIsAndSubject_IdInOrderByUpdatedDateDesc(FeedbackStatus status, List<String> subjectId, Pageable pageable);

	Mono<Long> countByStatusAndSubject_IdIn(FeedbackStatus status, List<String> actorId);
}
