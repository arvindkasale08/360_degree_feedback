package com.intuit.review.user.mgmt.port.mongo.repository;

import java.util.Arrays;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.review.user.mgmt.domain.bo.common.ErrorBo;
import com.intuit.review.user.mgmt.domain.bo.common.ErrorDetailBo;
import com.intuit.review.user.mgmt.domain.bo.common.UserStatus;
import com.intuit.review.user.mgmt.domain.bo.exception.ErrorConstants;
import com.intuit.review.user.mgmt.domain.bo.exception.ServiceException;
import com.intuit.review.user.mgmt.domain.bo.user.UserBo;
import com.intuit.review.user.mgmt.port.mongo.mapper.UserBoEntityMapper;
import com.intuit.review.user.mgmt.service.port.mongo.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository {

	private final UserMongoRepository repository;
	private final UserBoEntityMapper mapper;

	@Override
	public Mono<UserBo> getUserByExternalId(String externalId) {
		log.info("Fetching user with id={} from database", externalId);
		return repository.findDistinctFirstByExternalId(externalId)
			.filter(user -> user.getStatus() == UserStatus.ACTIVE)
			.map(mapper::mapEntityToBo);
	}

	@Override
	public Flux<UserBo> getDirectReportingForManager(String managerId) {
		log.info("Getting all direct reporings for managerId={}", managerId);
		return repository.findAllByManagerIdIs(managerId)
			.doOnError(throwable -> {
				log.error("Cannot get initialized feedbacks", throwable);
				ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR).status(500)
					.message("Cannot get initialized feedbacks")
					.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR)
						.message(throwable.getMessage()).build()))
					.build();
				throw new ServiceException(errorBo);
			})
			.map(mapper::mapEntityToBo);
	}
}
