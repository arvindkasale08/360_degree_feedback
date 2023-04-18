package com.intuit.review.feedback.mgmt.port.http.exception;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;

import com.intuit.review.feedback.mgmt.domain.bo.common.ErrorBo;
import com.intuit.review.feedback.mgmt.domain.exception.ServiceException;
import reactor.core.publisher.Mono;

@Slf4j
public class ExceptionHandler {

	public static final String NO_RESPONSE = "NO_RESPONSE";

	public static boolean isNotSuccess(HttpStatus httpStatus) {
		return !httpStatus.is2xxSuccessful();
	}

	public static boolean isUnAuthorized(HttpStatus httpStatus) {
		return httpStatus == HttpStatus.UNAUTHORIZED || httpStatus == HttpStatus.FORBIDDEN;
	}

	public static boolean isConflict(HttpStatus httpStatus) {
		return httpStatus == HttpStatus.CONFLICT;
	}

	// Appwise sometimes returns bad gateways and passes on retries this handles that issue
	public static boolean isRecoverable(HttpStatus httpStatus) {
		return httpStatus == HttpStatus.BAD_GATEWAY || httpStatus == HttpStatus.SERVICE_UNAVAILABLE || httpStatus == HttpStatus.GATEWAY_TIMEOUT;
	}

	public static Mono<? extends Throwable> handleNonSuccess(ClientResponse response) {
		return response.bodyToMono(String.class)
			.defaultIfEmpty(NO_RESPONSE)
			.map(s -> {
				log.error("API call failed with error={}", s);
				ErrorBo errorBo = ErrorBo
					.builder()
					.code(response.statusCode().toString())
					.status(response.statusCode().value())
					.message(s)
					.build();
				return new ServiceException(errorBo);
			});
	}
}
