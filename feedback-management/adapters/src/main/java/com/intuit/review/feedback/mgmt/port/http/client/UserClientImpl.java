package com.intuit.review.feedback.mgmt.port.http.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.intuit.review.feedback.mgmt.port.http.exception.ExceptionHandler;
import com.intuit.review.feedback.mgmt.port.http.model.UserListResponseDTO;
import com.intuit.review.feedback.mgmt.port.http.model.UserResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class UserClientImpl implements UserClient {

	private final WebClient webClient;

	@Override
	public Mono<UserResponseDTO> getUserByExternalId(String externalId) {
		return webClient
			.get()
			.uri("/api/account/v1/users/".concat(externalId))
			.retrieve()
			//.onStatus(ExceptionHandler::isNotSuccess, ExceptionHandler::handleNonSuccess)
			.bodyToMono(UserResponseDTO.class)
			.doOnNext(userConnectionResponseDto -> log.info("Returning response from UserClientImpl::getUserByExternalId"));
	}

	@Override
	public Mono<UserListResponseDTO> getDirectReportingByManagerId(String managerId) {
		return webClient
			.get()
			.uri("/api/account/v1/users/".concat(managerId).concat("/directReporting"))
			.retrieve()
			//.onStatus(ExceptionHandler::isNotSuccess, ExceptionHandler::handleNonSuccess)
			.bodyToMono(UserListResponseDTO.class)
			.doOnNext(userConnectionResponseDto -> log.info("Returning response from UserClientImpl::getUserByExternalId"));
	}
}
