package com.intuit.feedback.user.mgmt.domain.bo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileBo {

	private String email;
	private String businessUnitId;
	private String designationId;
	// reference to a user whose profile this is
	private String userId;
}
