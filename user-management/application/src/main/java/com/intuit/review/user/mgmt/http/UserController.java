package com.intuit.review.user.mgmt.http;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.review.user.mgmt.port.http.UserHandler;
import com.intuit.review.user.mgmt.port.http.model.UserListResponseDTO;
import com.intuit.review.user.mgmt.port.http.model.UserResponseDTO;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping(value = "/api/account/v1/users/")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	private UserHandler userHandler;

	@RequestMapping(value = "{externalId}", method = RequestMethod.GET)
	public Mono<ResponseEntity<UserResponseDTO>> getUserByExternalId(@Valid @PathVariable("externalId") String externalId) {
		return userHandler.handleGetByExternalId(externalId)
			.map(userResponseDto -> new ResponseEntity<>(userResponseDto, HttpStatus.OK));
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Mono<ResponseEntity<UserListResponseDTO>> getAllUsers() {
		return userHandler.handleGetAll()
			.map(userListResponseDTO -> new ResponseEntity<>(userListResponseDTO, HttpStatus.OK));
	}

	@RequestMapping(value = "{externalId}/directReporting", method = RequestMethod.GET)
	public Mono<ResponseEntity<UserListResponseDTO>> getDirectReportingForManager(@Valid @PathVariable("externalId") String externalId) {
		return userHandler.getDirectReportingForManager(externalId)
			.map(userListResponseDTO -> new ResponseEntity<>(userListResponseDTO, HttpStatus.OK));
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public Mono<ResponseEntity<UserListResponseDTO>> search(@RequestParam(value = "searchText", required = true) String searchText) {
		return userHandler.search(searchText)
			.map(userListResponseDTO -> new ResponseEntity<>(userListResponseDTO, HttpStatus.OK));
	}
}
