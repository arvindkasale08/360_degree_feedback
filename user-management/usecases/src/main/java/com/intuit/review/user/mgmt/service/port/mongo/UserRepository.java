package com.intuit.review.user.mgmt.service.port.mongo;

import com.intuit.review.user.mgmt.domain.bo.user.UserBo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

	Mono<UserBo> getUserByExternalId(String externalId);

	Flux<UserBo> getDirectReportingForManager(String managerId);
}
