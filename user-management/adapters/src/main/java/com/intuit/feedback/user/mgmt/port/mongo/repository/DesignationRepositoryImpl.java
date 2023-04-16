package com.intuit.feedback.user.mgmt.port.mongo.repository;

import java.util.Arrays;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Sort;

import com.intuit.feedback.user.mgmt.domain.bo.common.ErrorBo;
import com.intuit.feedback.user.mgmt.domain.bo.common.ErrorDetailBo;
import com.intuit.feedback.user.mgmt.domain.bo.exception.DuplicateKeyException;
import com.intuit.feedback.user.mgmt.domain.bo.exception.ErrorConstants;
import com.intuit.feedback.user.mgmt.domain.bo.user.DesignationBo;
import com.intuit.feedback.user.mgmt.domain.ports.persistence.DesignationRepository;
import com.intuit.feedback.user.mgmt.port.mongo.mapper.DesignationBoEntityMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class DesignationRepositoryImpl implements DesignationRepository {

	private final DesignationMongoRepository repository;
	private final DesignationBoEntityMapper mapper;

	@Override
	public Mono<DesignationBo> createDesignation(DesignationBo designationBo) {
		log.info("Saving designation with code={} to database", designationBo.getCode());
		return Mono.just(mapper.mapBoToEntity(designationBo))
			.flatMap(repository::insert)
			.doOnError(org.springframework.dao.DuplicateKeyException.class, error -> {
				log.error("Business Unit already registered", error);
				ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.BAD_REQUEST).status(400)
					.message("Designation with code" + designationBo.getCode() +" already in use, duplicate entry cannot be created")
					.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.DUPLICATE_KEY_ERROR)
						.message(error.getMessage()).build()))
					.build();
				throw new DuplicateKeyException(errorBo);
			})
			.map(designation -> mapper.mapEntityToBo(designation));
	}

	@Override
	public Mono<DesignationBo> updateDesignation(DesignationBo designationBo) {
		log.info("Updating designation with code={} to database", designationBo.getCode());
		return Mono.just(mapper.mapBoToEntity(designationBo))
			.flatMap(repository::save)
			.doOnError(DuplicateKeyException.class, error -> {
				log.error("Business Unit already registered", error);
				ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.BAD_REQUEST).status(400)
					.message("Designation with code" + designationBo.getCode() +" already in use, duplicate entry cannot be created")
					.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.DUPLICATE_KEY_ERROR)
						.message(error.getMessage()).build()))
					.build();
				throw new DuplicateKeyException(errorBo);
			})
			.map(designation -> mapper.mapEntityToBo(designation));
	}

	@Override
	public Mono<DesignationBo> readDesignation(String id) {
		log.info("Reading Business Unit with id={}", id);
		return repository
			.findById(id)
			.map(designation -> mapper.mapEntityToBo(designation));
	}

	@Override
	public Flux<DesignationBo> readDesignations() {
		log.info("Reading all Business Units");
		return repository.findAll(Sort.by(Sort.Direction.DESC, "id"))
			.map(designation -> mapper.mapEntityToBo(designation));
	}

	@Override
	public Mono<DesignationBo> readDesignationByCode(String code) {
		log.info("Reading Business Unit with code={}", code);
		return repository.findFirstByCode(code)
			.map(designation -> mapper.mapEntityToBo(designation));
	}
}
