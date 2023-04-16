package com.intuit.feedback.user.mgmt.persistence.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.feedback.user.mgmt.domain.bo.user.BusinessUnitBo;
import com.intuit.feedback.user.mgmt.domain.ports.persistence.BusinessUnitRepository;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class BusinessUnitRepositoryImpl implements BusinessUnitRepository {

	private final BusinessUnitRepository repository;

	@Override
	public Mono<BusinessUnitBo> createBusinessUnit(BusinessUnitBo businessUnitBo) {
		log.info("Saving business unit with code={} to database", businessUnitBo.getCode());
		return null;
	}

	@Override
	public Mono<BusinessUnitBo> updateBusinessUnit(BusinessUnitBo businessUnitBo) {
		return null;
	}

	@Override
	public Mono<BusinessUnitBo> readBusinessUnit(String id) {
		return null;
	}

	@Override
	public Mono<BusinessUnitBo> readBusinessUnitByCode(String code) {
		return null;
	}
}
