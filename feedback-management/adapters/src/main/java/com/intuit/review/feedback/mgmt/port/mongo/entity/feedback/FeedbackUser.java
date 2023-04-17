package com.intuit.review.feedback.mgmt.port.mongo.entity.feedback;

import org.springframework.data.mongodb.core.index.Indexed;

public class FeedbackUser {
	@Indexed
	private String id;
	private String email;
	private String firstName;
	private String lastName;
}
