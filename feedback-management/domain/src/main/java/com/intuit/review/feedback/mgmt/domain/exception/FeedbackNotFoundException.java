package com.intuit.review.feedback.mgmt.domain.exception;

import lombok.Getter;

import com.intuit.review.feedback.mgmt.domain.bo.common.ErrorBo;

@Getter
public final class FeedbackNotFoundException extends ResourceNotFoundException {
	private static final long serialVersionUID = 1L;
	private final ErrorBo errorBo;

	public FeedbackNotFoundException(String message) {
		super(message);
		this.errorBo = ErrorBo.builder().code("BAD_REQUEST").message(message).build();
	}

	public FeedbackNotFoundException(ErrorBo errorBo) {
		super(errorBo.getMessage());
		this.errorBo = errorBo;
	}
}
