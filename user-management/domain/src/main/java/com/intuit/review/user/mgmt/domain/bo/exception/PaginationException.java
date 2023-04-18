package com.intuit.review.user.mgmt.domain.bo.exception;

import lombok.Getter;

import com.intuit.review.user.mgmt.domain.bo.common.ErrorBo;

@Getter
public final class PaginationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final ErrorBo errorBo;

	public PaginationException(String message) {
		super(message);
		this.errorBo = ErrorBo.builder().message(message).code("PAGINATION_ERROR").build();
	}

	public PaginationException(ErrorBo errorBo) {
		super(errorBo.getMessage());
		this.errorBo = errorBo;
	}
}
