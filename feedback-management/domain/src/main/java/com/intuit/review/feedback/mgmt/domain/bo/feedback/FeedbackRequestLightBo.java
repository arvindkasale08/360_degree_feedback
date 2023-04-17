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
public class FeedbackRequestLightBo extends AuditableBo {

	private String requestorId; // User requesting the feedback
	private String subjectId; // User who is the subject of the feedback
	private String actorId; // User who is supposed to give the feedback
}
