package com.intuit.review.user.mgmt.port.mongo.repository;

import java.util.Arrays;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Sort;

import com.intuit.review.user.mgmt.domain.bo.common.ErrorBo;
import com.intuit.review.user.mgmt.domain.bo.common.ErrorDetailBo;
import com.intuit.review.user.mgmt.domain.bo.exception.DuplicateKeyException;
import com.intuit.review.user.mgmt.domain.bo.exception.ErrorConstants;
import com.intuit.review.user.mgmt.domain.bo.user.BusinessUnitBo;
import com.intuit.review.user.mgmt.port.mongo.mapper.BusinessUnitBoEntityMapper;
import com.intuit.review.user.mgmt.service.port.mongo.BusinessUnitRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class BusinessUnitRepositoryImpl implements BusinessUnitRepository {

	private final BusinessUnitMongoRepository repository;
	private final BusinessUnitBoEntityMapper mapper;

	@Override
	public Mono<BusinessUnitBo> createBusinessUnit(BusinessUnitBo businessUnitBo) {
		log.info("Saving business unit with code={} to database", businessUnitBo.getCode());
		return Mono.just(mapper.mapBoToEntity(businessUnitBo))
			.flatMap(repository::insert)
			.doOnError(org.springframework.dao.DuplicateKeyException.class, error -> {
				log.error("Business Unit already registered", error);
				ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.BAD_REQUEST).status(400)
					.message("Business unit with code" + businessUnitBo.getCode() +" already in use, duplicate entry cannot be created")
					.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.DUPLICATE_KEY_ERROR)
						.message(error.getMessage()).build()))
					.build();
				throw new DuplicateKeyException(errorBo);
			})
			.map(businessUnit -> mapper.mapEntityToBo(businessUnit));
	}

	@Override
	public Mono<BusinessUnitBo> updateBusinessUnit(BusinessUnitBo businessUnitBo) {
		log.info("Updating business unit with code={} to database", businessUnitBo.getCode());
		return Mono.just(mapper.mapBoToEntity(businessUnitBo))
			.flatMap(repository::save)
			.doOnError(DuplicateKeyException.class, error -> {
				log.error("Business Unit already registered", error);
				ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.BAD_REQUEST).status(400)
					.message("Business unit with code" + businessUnitBo.getCode() +" already in use, duplicate entry cannot be created")
					.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.DUPLICATE_KEY_ERROR)
						.message(error.getMessage()).build()))
					.build();
				throw new DuplicateKeyException(errorBo);
			})
			.map(businessUnit -> mapper.mapEntityToBo(businessUnit));
	}

	@Override
	public Mono<BusinessUnitBo> readBusinessUnit(String id) {
		log.info("Reading Business Unit with id={}", id);
		return repository
			.findById(id)
			.map(businessUnit -> mapper.mapEntityToBo(businessUnit));
	}

	@Override
	public Flux<BusinessUnitBo> readBusinessUnits() {
		log.info("Reading all Business Units");
		return repository.findAll(Sort.by(Sort.Direction.DESC, "id"))
			.map(businessUnit -> mapper.mapEntityToBo(businessUnit));
	}

	@Override
	public Mono<BusinessUnitBo> readBusinessUnitByCode(String code) {
		log.info("Reading Business Unit with code={}", code);
		return repository.findFirstByCode(code)
			.map(businessUnit -> mapper.mapEntityToBo(businessUnit));
	}
}
