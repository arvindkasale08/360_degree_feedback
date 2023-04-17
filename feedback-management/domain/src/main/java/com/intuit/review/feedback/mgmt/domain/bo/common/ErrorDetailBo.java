package com.intuit.review.feedback.mgmt.domain.bo.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetailBo {

	private String code;
	private String message;
	private String field;
}

