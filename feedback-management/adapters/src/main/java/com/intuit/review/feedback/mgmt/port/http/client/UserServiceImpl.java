package com.intuit.review.feedback.mgmt.port.http.client;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackUserBo;
import com.intuit.review.feedback.mgmt.service.port.http.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class UserServiceImpl implements UserService {

	@Override
	public Mono<FeedbackUserBo> getUser(String id) {
		// TODO : Update with real implementation
		return Mono.just(FeedbackUserBo.builder()
				.email("dummy-email.com")
				.firstName("John")
				.lastName("Doe")
				.id("123456")
			.build());
	}

	@Override
	public Flux<FeedbackUserBo> getDirectReporting(String managerId) {
		return Flux.fromIterable(
			Arrays.asList(FeedbackUserBo.builder()
				.email("dummy-email.com")
				.firstName("John")
				.lastName("Doe")
				.id("123456")
				.build())
		);
	}
}
