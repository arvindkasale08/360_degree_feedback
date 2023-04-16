package com.intuit.feedback.user.mgmt.domain.port.service;

import com.intuit.feedback.user.mgmt.domain.bo.user.BusinessUnitBo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BusinessUnitService {

	Mono<BusinessUnitBo> createBusinessUnit(BusinessUnitBo businessUnitBo);

	Mono<BusinessUnitBo> updateBusinessUnit(BusinessUnitBo businessUnitBo);

	Mono<BusinessUnitBo> readBusinessUnit(String id);

	Flux<BusinessUnitBo> readBusinessUnits();

	Mono<BusinessUnitBo> readBusinessUnitByCode(String code);
}
