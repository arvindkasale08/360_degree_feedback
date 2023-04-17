package com.intuit.review.feedback.mgmt.port.mongo.entity.feedback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackRequestStatus;
import com.intuit.review.feedback.mgmt.port.mongo.entity.common.AuditableEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "feedback_request")
@EqualsAndHashCode(callSuper=false)
public class FeedbackRequest extends AuditableEntity {
	@Id
	private String id;
	private FeedbackUser requestor; // User requesting the feedback
	private FeedbackUser subject; // User who is the subject of the feedback
	private FeedbackUser actor; // User who is supposed to give the feedback
	private long validTill;
	private FeedbackRequestStatus status;
}
