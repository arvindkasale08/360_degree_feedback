package com.intuit.review.user.mgmt.port.mongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.intuit.review.user.mgmt.port.mongo.entity.user.Designation;
import reactor.core.publisher.Mono;

public interface DesignationMongoRepository extends ReactiveMongoRepository<Designation, String> {

	Mono<Designation> findFirstByCode(String code);
}
