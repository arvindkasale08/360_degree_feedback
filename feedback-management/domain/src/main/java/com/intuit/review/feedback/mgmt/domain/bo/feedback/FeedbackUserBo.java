package com.intuit.review.feedback.mgmt.domain.bo.feedback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackUserBo {

	private String id;
	private String email;
	private String firstName;
	private String lastName;
}
