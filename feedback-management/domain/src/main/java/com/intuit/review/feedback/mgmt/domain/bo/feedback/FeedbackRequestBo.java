package com.intuit.review.feedback.mgmt.domain.bo.feedback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.intuit.review.feedback.mgmt.domain.bo.common.AuditableBo;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FeedbackRequestBo extends AuditableBo {

	private String id;
	private FeedbackUserBo requestor; // User requesting the feedback
	private FeedbackUserBo subject; // User who is the subject of the feedback
	private FeedbackUserBo actor; // User who is supposed to give the feedback
	private long validTill;
	private FeedbackRequestStatus status;
}
