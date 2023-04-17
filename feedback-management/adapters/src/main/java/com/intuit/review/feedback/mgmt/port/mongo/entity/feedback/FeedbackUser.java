package com.intuit.review.feedback.mgmt.port.mongo.entity.feedback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.index.Indexed;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackUser {
	@Indexed
	private String id;
	private String email;
	private String firstName;
	private String lastName;
}
