package com.intuit.feedback.user.mgmt.port.http;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.feedback.user.mgmt.port.api.model.DesignationRequestDTO;
import com.intuit.feedback.user.mgmt.port.api.model.DesignationResponseDTO;
import com.intuit.feedback.user.mgmt.port.http.mapper.DesignationRequestDtoBoMapper;
import com.intuit.feedback.user.mgmt.port.http.mapper.DesignationResponseDtoBoMapper;
import com.intuit.feedback.user.mgmt.service.account.DesignationUC;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class DesignationHandler {

	private DesignationUC designationUC;
	private DesignationRequestDtoBoMapper requestDtoBoMapper;
	private DesignationResponseDtoBoMapper responseDtoBoMapper;

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
