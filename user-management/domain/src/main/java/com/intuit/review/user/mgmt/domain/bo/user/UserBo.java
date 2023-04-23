package com.intuit.review.user.mgmt.domain.bo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.intuit.review.user.mgmt.domain.bo.common.AuditableBo;
import com.intuit.review.user.mgmt.domain.bo.common.UserStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class UserBo extends AuditableBo {

	private String id;
	private String externalId;
	private String username;
	private String managerId;
	private ProfileBo profile;
	private UserStatus status;
}
