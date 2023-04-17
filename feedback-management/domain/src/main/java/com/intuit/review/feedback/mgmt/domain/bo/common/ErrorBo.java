package com.intuit.review.feedback.mgmt.domain.bo.common;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorBo {

	private String code;
	private String message;
	private Integer status;
	private List<ErrorDetailBo> details;
}
