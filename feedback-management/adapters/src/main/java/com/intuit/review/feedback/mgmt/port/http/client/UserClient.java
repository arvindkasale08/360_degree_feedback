package com.intuit.review.feedback.mgmt.port.http.client;

import com.intuit.review.feedback.mgmt.port.http.model.UserListResponseDTO;
import com.intuit.review.feedback.mgmt.port.http.model.UserResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserClient {

	Mono<UserResponseDTO> getUserByExternalId(String externalId);

	Mono<UserListResponseDTO> getDirectReportingByManagerId(String managerId);
}
