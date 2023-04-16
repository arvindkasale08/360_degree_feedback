package com.intuit.feedback.user.mgmt.domain.bo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.intuit.feedback.user.mgmt.domain.bo.common.AuditableBo;
import com.intuit.feedback.user.mgmt.domain.bo.common.UserStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBo extends AuditableBo {

	private String id;
	private String username;
	private String password;
	private String managerId;
	private String managerName;
	private UserStatus status;
}
