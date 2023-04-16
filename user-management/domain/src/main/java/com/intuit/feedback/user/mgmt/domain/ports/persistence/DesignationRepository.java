package com.intuit.feedback.user.mgmt.domain.ports.persistence;

import com.intuit.feedback.user.mgmt.domain.bo.user.DesignationBo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DesignationRepository {

	Mono<DesignationBo> createDesignation(DesignationBo designationBo);

	Mono<DesignationBo> updateDesignation(DesignationBo designationBo);

	Mono<DesignationBo> readDesignation(String id);

	Flux<DesignationBo> readDesignations();

	Mono<DesignationBo> readDesignationByCode(String code);
}
