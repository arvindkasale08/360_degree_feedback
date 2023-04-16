package com.intuit.feedback.user.mgmt.persistence.utils;

import com.intuit.feedback.user.mgmt.persistence.entity.user.BusinessUnit;

public class DBDatasetCreator {

	// get entities
	public static BusinessUnit getBusinessUnit(String id, String name, String code) {
		return BusinessUnit
			.builder()
			.id(id)
			.name(name)
			.code(code)
			.build();
	}
}
