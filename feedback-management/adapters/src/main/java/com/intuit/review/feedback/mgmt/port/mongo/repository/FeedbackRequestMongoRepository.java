package com.intuit.review.feedback.mgmt.port.mongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.intuit.review.feedback.mgmt.port.mongo.entity.feedback.FeedbackRequest;

public interface FeedbackRequestMongoRepository extends ReactiveMongoRepository<FeedbackRequest, String> {
}
