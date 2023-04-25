package com.intuit.review.feedback.mgmt.domain.exception;

public class EventPublishException extends RuntimeException{

	public EventPublishException(String message, Throwable cause) {
		super(message, cause);
	}

	public EventPublishException(String message) {
		super(message);
	}

}
