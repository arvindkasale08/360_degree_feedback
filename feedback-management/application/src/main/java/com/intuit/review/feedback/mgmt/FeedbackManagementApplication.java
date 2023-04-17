package com.intuit.review.feedback.mgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.intuit.review.feedback.mgmt.application", "com.intuit.review.feedback.mgmt.port.mongo"})
public class FeedbackManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedbackManagementApplication.class, args);
	}

}
