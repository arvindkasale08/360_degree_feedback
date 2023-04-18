package com.intuit.review.feedback.mgmt.port.http.client;

import java.util.Arrays;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackUserBo;
import com.intuit.review.feedback.mgmt.port.http.mapper.UserResponseDTOtoFeedbackUserBoMapper;
import com.intuit.review.feedback.mgmt.service.port.http.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserClient userClient;
	private final UserResponseDTOtoFeedbackUserBoMapper mapper;

	@Override
	public Mono<FeedbackUserBo> getUser(String externalId) {
		log.info("Calling user service to get user for externalId={}", externalId);
		return userClient.getUserByExternalId(externalId)
			.map(mapper::mapDtoToBo);
	}

	@Override
	public Flux<FeedbackUserBo> getDirectReporting(String managerId) {
		log.info("Calling user service to get all direct reportings for managerId={}", managerId);
		return userClient.getDirectReportingByManagerId(managerId)
			.flatMapMany(userListResponseDTO -> Flux.fromIterable(userListResponseDTO.getUsers()))
			.map(mapper::mapDtoToBo);
	}
}
