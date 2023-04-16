package com.intuit.feedback.user.mgmt.persistence.entity.common;

import lombok.Data;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
public class AuditableEntity {

	@CreatedDate
	private Long createdDate;
	@LastModifiedDate
	private Long updatedDate;
}
