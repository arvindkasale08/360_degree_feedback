package com.intuit.review.user.mgmt.port.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.review.user.mgmt.port.http.mapper.UserResponseDtoBoMapper;
import com.intuit.review.user.mgmt.port.http.model.UserListResponseDTO;
import com.intuit.review.user.mgmt.port.http.model.UserResponseDTO;
import com.intuit.review.user.mgmt.service.account.UserUC;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class UserHandler {

	private final UserUC userUC;
	private final UserResponseDtoBoMapper responseDtoBoMapper;

	public Mono<UserResponseDTO> handleGetByExternalId(String externalId) {
		log.info("UserHandler:: getting user for externalId={}", externalId);
		return userUC.getUserByExternalId(externalId)
			.map(responseDtoBoMapper::mapBoToDto);
	}

	public Mono<UserListResponseDTO> handleGetAll() {
		return userUC.getAllUsers()
			.map(responseDtoBoMapper::mapBoToDto)
			.collectList()
			.map(users -> {
				UserListResponseDTO responseDTO = new UserListResponseDTO();
				responseDTO.users(users);
				return responseDTO;
			});
	}

	public Mono<UserListResponseDTO> getDirectReportingForManager(String managerId) {
		return userUC.getDirectReportingForManager(managerId)
			.map(responseDtoBoMapper::mapBoToDto)
			.collectList()
			.map(users -> {
				UserListResponseDTO responseDTO = new UserListResponseDTO();
				responseDTO.users(users);
				return responseDTO;
			});
	}
}
