package com.intuit.review.feedback.mgmt.port.mongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.intuit.review.feedback.mgmt.port.mongo.entity.feedback.FeedbackRequest;

@Repository
public interface FeedbackRequestMongoRepository extends ReactiveMongoRepository<FeedbackRequest, String> {
}
