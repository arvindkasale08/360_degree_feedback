package com.intuit.review.feedback.mgmt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intuit.review.feedback.mgmt.service.feedback.FeedbackRequestUC;
import com.intuit.review.feedback.mgmt.service.port.http.UserService;
import com.intuit.review.feedback.mgmt.service.port.mongo.FeedbackRequestRepository;

@Configuration
public class UseCaseConfiguration {

	@Bean
	public FeedbackRequestUC feedbackRequestUC(FeedbackRequestRepository feedbackRequestRepository, UserService userService) {
		return new FeedbackRequestUC(feedbackRequestRepository, userService);
	}
}
