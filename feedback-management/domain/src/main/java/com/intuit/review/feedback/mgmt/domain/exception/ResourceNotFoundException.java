package com.intuit.review.feedback.mgmt.domain.exception;

import lombok.Getter;

import com.intuit.review.feedback.mgmt.domain.bo.common.ErrorBo;

@Getter
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final ErrorBo errorBo;

	public ResourceNotFoundException(String message) {
		super(message);
		this.errorBo = ErrorBo.builder().message(message).code("RESOURCE_NOT_FOUND").build();
	}

	public ResourceNotFoundException(ErrorBo errorBo) {
		super(errorBo.getMessage());
		this.errorBo = errorBo;
	}
}
