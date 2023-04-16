package com.intuit.feedback.user.mgmt.domain.ports.persistence;

import com.intuit.feedback.user.mgmt.domain.bo.user.BusinessUnitBo;
import reactor.core.publisher.Mono;

public interface BusinessUnitRepository {

	Mono<BusinessUnitBo> createBusinessUnit(BusinessUnitBo businessUnitBo);

	Mono<BusinessUnitBo> updateBusinessUnit(BusinessUnitBo businessUnitBo);

	Mono<BusinessUnitBo> readBusinessUnit(String id);

	Mono<BusinessUnitBo> readBusinessUnitByCode(String code);
}
