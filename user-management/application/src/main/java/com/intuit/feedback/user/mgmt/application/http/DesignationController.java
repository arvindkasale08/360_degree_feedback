package com.intuit.feedback.user.mgmt.application.http;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.feedback.user.mgmt.port.api.model.DesignationRequestDTO;
import com.intuit.feedback.user.mgmt.port.api.model.DesignationResponseDTO;
import com.intuit.feedback.user.mgmt.port.http.DesignationHandler;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping(value = "/api/account/v1/designations")
public class DesignationController {

	@Autowired
	private DesignationHandler designationHandler;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Mono<ResponseEntity<DesignationResponseDTO>> createDesignation(@Valid @RequestBody DesignationRequestDTO request) {
		return designationHandler.handleCreate(request)
			.map(designationResponseDTO -> new ResponseEntity<>(designationResponseDTO, HttpStatus.OK));
	}

	@RequestMapping(value = "/{code}", method = RequestMethod.PUT)
	public Mono<ResponseEntity<DesignationResponseDTO>> updateDesignation(@Valid @PathVariable("code") String code, @Valid @RequestBody DesignationRequestDTO request) {
		return designationHandler.handleUpdate(code, request)
			.map(designationResponseDTO -> new ResponseEntity<>(designationResponseDTO, HttpStatus.OK));
	}
}
