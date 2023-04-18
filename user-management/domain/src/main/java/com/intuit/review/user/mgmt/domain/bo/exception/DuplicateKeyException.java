package com.intuit.review.user.mgmt.domain.bo.exception;

import lombok.Getter;

import com.intuit.review.user.mgmt.domain.bo.common.ErrorBo;

@Getter
public final class DuplicateKeyException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final ErrorBo errorBo;

	public DuplicateKeyException(String message) {
		super(message);
		this.errorBo = ErrorBo.builder().code("DUPLICATE_KEY").message(message).build();
	}

	public DuplicateKeyException(ErrorBo errorBo) {
		super(errorBo.getMessage());
		this.errorBo = errorBo;
	}

}
