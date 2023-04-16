package com.intuit.feedback.user.mgmt.persistence.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.intuit.feedback.user.mgmt.persistence.entity.user.BusinessUnit;
import reactor.core.publisher.Mono;

public interface BusinessUnitMongoRepository extends ReactiveMongoRepository<BusinessUnit, String> {

	Mono<BusinessUnit> findFirstByCode(String code);
}
