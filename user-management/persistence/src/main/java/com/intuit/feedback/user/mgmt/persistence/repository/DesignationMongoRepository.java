package com.intuit.feedback.user.mgmt.persistence.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.intuit.feedback.user.mgmt.persistence.entity.user.Designation;
import reactor.core.publisher.Mono;

public interface DesignationMongoRepository extends ReactiveMongoRepository<Designation, String> {

	Mono<Designation> findFirstByCode(String code);
}
