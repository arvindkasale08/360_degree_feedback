package com.intuit.review.user.mgmt.port.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.intuit.review.user.mgmt.port.mongo.entity.user.BusinessUnit;
import com.intuit.review.user.mgmt.port.mongo.entity.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserMongoRepository extends ReactiveMongoRepository<User, String> {

	Mono<User> findDistinctFirstByExternalId(String externalId);

	Flux<User> findAllByManagerIdIs(String managerId);

	Flux<User> findAllByProfile_FirstNameInOrProfile_LastNameIn(List<String> lst1, List<String> lst2);
}
