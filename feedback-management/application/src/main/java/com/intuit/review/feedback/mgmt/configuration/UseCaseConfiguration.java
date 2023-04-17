package com.intuit.review.feedback.mgmt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intuit.review.feedback.mgmt.service.feedback.FeedbackUC;
import com.intuit.review.feedback.mgmt.service.port.http.UserService;
import com.intuit.review.feedback.mgmt.service.port.mongo.FeedbackRepository;

@Configuration
public class UseCaseConfiguration {

	@Bean
	public FeedbackUC feedbackRequestUC(FeedbackRepository feedbackRepository, UserService userService) {
		return new FeedbackUC(feedbackRepository, userService);
	}
}
