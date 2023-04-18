package com.intuit.review.user.mgmt.service.port.mongo;

import com.intuit.review.user.mgmt.domain.bo.user.DesignationBo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DesignationRepository {

	Mono<DesignationBo> createDesignation(DesignationBo designationBo);

	Mono<DesignationBo> updateDesignation(DesignationBo designationBo);

	Mono<DesignationBo> readDesignation(String id);

	Flux<DesignationBo> readDesignations();

	Mono<DesignationBo> readDesignationByCode(String code);
}
