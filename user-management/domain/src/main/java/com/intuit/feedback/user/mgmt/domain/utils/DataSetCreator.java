package com.intuit.feedback.user.mgmt.domain.utils;

import com.intuit.feedback.user.mgmt.domain.bo.user.BusinessUnitBo;

public class DataSetCreator {


	// get bos
	public static BusinessUnitBo getBusinessUnitBo(String id, String name, String code) {
		return BusinessUnitBo
			.builder()
			.id(id)
			.name(name)
			.code(code)
			.build();
	}
}
