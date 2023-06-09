package com.intuit.review.user.mgmt.domain.bo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileBo {

	private String firstName;
	private String lastName;
	private String email;
	private String businessUnit;
	private String designation;
	private String imageUrl;
}
