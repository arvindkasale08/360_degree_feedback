package com.intuit.review.user.mgmt.http;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.intuit.review.user.mgmt.domain.bo.exception.ResourceNotFoundException;
import com.intuit.review.user.mgmt.domain.bo.exception.ServiceException;
import com.intuit.review.user.mgmt.domain.bo.exception.UserNotFoundException;
import com.intuit.review.user.mgmt.http.mapper.ErrorDtoBoMapper;
import com.intuit.review.user.mgmt.port.http.model.ErrorDTO;

@Slf4j
@RestControllerAdvice
public final class ExceptionHandlerControllerAdvice {

	@Autowired
	ErrorDtoBoMapper errorDtoBoMapper;

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ServiceException.class)
	ResponseEntity<ErrorDTO> handleServiceException(ServiceException ex) {
		log.info("Creating response with internal server error status. " + ex.getErrorBo().getMessage());

		ErrorDTO errorDto = errorDtoBoMapper.getBoToDtoMapper().apply(ex.getErrorBo());
		errorDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
	}

	@ExceptionHandler({UserNotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ResponseEntity<ErrorDTO> handleUserNotFound(ResourceNotFoundException ex) {
		log.info("Creating FeedbackNotFoundException response with not found status. " + ex.getErrorBo().getMessage());

		ErrorDTO errorDto = errorDtoBoMapper.getBoToDtoMapper().apply(ex.getErrorBo());
		errorDto.setStatus(HttpStatus.NOT_FOUND.value());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
	}
}
