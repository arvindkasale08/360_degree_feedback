package com.intuit.review.user.mgmt.domain.bo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessUnitBo {
	private String id;
	private String name;
	private String code;

}
