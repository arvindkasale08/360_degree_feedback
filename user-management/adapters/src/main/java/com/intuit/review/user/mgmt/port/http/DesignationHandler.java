package com.intuit.review.user.mgmt.port.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.review.user.mgmt.port.http.model.DesignationRequestDTO;
import com.intuit.review.user.mgmt.port.http.model.DesignationResponseDTO;
import com.intuit.review.user.mgmt.port.http.mapper.DesignationRequestDtoBoMapper;
import com.intuit.review.user.mgmt.port.http.mapper.DesignationResponseDtoBoMapper;
import com.intuit.review.user.mgmt.service.account.DesignationUC;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class DesignationHandler {

	private final DesignationUC designationUC;
	private final DesignationRequestDtoBoMapper requestDtoBoMapper;
	private final DesignationResponseDtoBoMapper responseDtoBoMapper;

	public Mono<DesignationResponseDTO> handleCreate(DesignationRequestDTO requestDTO) {
		log.info("Designation Handler:: creating designation with code={}", requestDTO.getCode());
		return Mono.just(requestDtoBoMapper.mapDtoToBo(requestDTO))
			.flatMap(designationUC::createDesignation)
			.map(responseDtoBoMapper::mapBoToDto);
	}

	public Mono<DesignationResponseDTO> handleUpdate(String code, DesignationRequestDTO requestDTO) {
		log.info("Designation Handler:: updating designation with code={}", requestDTO.getCode());
		return Mono.just(requestDtoBoMapper.mapDtoToBo(requestDTO))
			.flatMap(designationUC::updateDesignation)
			.map(responseDtoBoMapper::mapBoToDto);
	}

}
