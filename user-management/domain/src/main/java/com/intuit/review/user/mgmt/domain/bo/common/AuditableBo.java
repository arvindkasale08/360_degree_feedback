package com.intuit.review.user.mgmt.domain.bo.common;

import java.io.Serializable;
import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "auditableBuilder")
public class AuditableBo implements Serializable {

	protected OffsetDateTime createdDate;

	protected OffsetDateTime updatedDate;
}
