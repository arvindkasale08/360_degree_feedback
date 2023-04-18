package com.intuit.review.user.mgmt.domain.bo.exception;

import lombok.Getter;

import com.intuit.review.user.mgmt.domain.bo.common.ErrorBo;

@Getter
public final class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final ErrorBo errorBo;

	public ServiceException(String message) {
		super(message);
		this.errorBo = ErrorBo.builder().message(message).code("SERVER_ERROR").build();
	}

	public ServiceException(ErrorBo errorBo) {
		super(errorBo.getMessage());
		this.errorBo = errorBo;
	}
}
