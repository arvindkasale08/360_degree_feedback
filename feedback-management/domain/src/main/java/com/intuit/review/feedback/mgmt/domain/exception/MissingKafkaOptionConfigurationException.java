package com.intuit.review.feedback.mgmt.domain.exception;

public class MissingKafkaOptionConfigurationException extends RuntimeException {

  public MissingKafkaOptionConfigurationException(String message) {
    super(message);
  }

  public MissingKafkaOptionConfigurationException(String message, Throwable cause) {
    super(message, cause);
  }
}
