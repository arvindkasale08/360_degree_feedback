package com.intuit.review.user.mgmt.domain.bo.exception;

import lombok.Getter;

import com.intuit.review.user.mgmt.domain.bo.common.ErrorBo;

@Getter
public final class UserNotFoundException extends ResourceNotFoundException {
	private static final long serialVersionUID = 1L;
	private final ErrorBo errorBo;

	public UserNotFoundException(String message) {
		super(message);
		this.errorBo = ErrorBo.builder().code("BAD_REQUEST").message(message).build();
	}

	public UserNotFoundException(ErrorBo errorBo) {
		super(errorBo.getMessage());
		this.errorBo = errorBo;
	}
}
