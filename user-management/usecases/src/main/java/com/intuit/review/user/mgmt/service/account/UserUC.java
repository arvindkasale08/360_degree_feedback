package com.intuit.review.user.mgmt.service.account;

import java.util.Arrays;

import lombok.RequiredArgsConstructor;

import com.intuit.review.user.mgmt.domain.bo.common.ErrorBo;
import com.intuit.review.user.mgmt.domain.bo.common.ErrorDetailBo;
import com.intuit.review.user.mgmt.domain.bo.exception.ErrorConstants;
import com.intuit.review.user.mgmt.domain.bo.exception.PaginationException;
import com.intuit.review.user.mgmt.domain.bo.exception.UserNotFoundException;
import com.intuit.review.user.mgmt.domain.bo.exception.ServiceException;
import com.intuit.review.user.mgmt.domain.bo.user.UserBo;
import com.intuit.review.user.mgmt.service.port.mongo.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUC {

	private final UserRepository userRepository;

	public Mono<UserBo> getUserByExternalId(String externalId) {
		// find the feedback in the database
		return userRepository.getUserByExternalId(externalId)
			// if not found return exception
			.switchIfEmpty(Mono.error(new UserNotFoundException(ErrorBo.builder().code(ErrorConstants.USER_NOT_FOUND).status(404)
				.message("User with externalId=".concat(externalId).concat(" does not exist")).build()
			)))
			.doOnError(error -> {
				if (!(error instanceof UserNotFoundException)) {
					ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR).status(500)
						.message("Error getting user")
						.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR)
							.message(error.getMessage()).build()))
						.build();
					throw new ServiceException(errorBo);
				}
			});
	}

	public Flux<UserBo> getAllUsers() {
		return userRepository
			.getAllUsers()
			.doOnError(error -> {
				ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR).status(500)
					.message("Error while fetching all users")
					.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR)
						.message(error.getMessage())
						.build())).build();
				throw new ServiceException(errorBo);
			});
	}

	public Flux<UserBo> getDirectReportingForManager(String managerId) {

		return userRepository
			.getDirectReportingForManager(managerId)
			.doOnError(error -> {
				ErrorBo errorBo = ErrorBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR).status(500)
					.message("Error while fetching direct reporings for manager ".concat(managerId))
					.details(Arrays.asList(ErrorDetailBo.builder().code(ErrorConstants.INTERNAL_SERVER_ERROR)
						.message(error.getMessage())
						.build())).build();
				throw new ServiceException(errorBo);
			});
	}
}
