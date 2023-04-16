package com.intuit.feedback.user.mgmt.port.utils;

import com.intuit.feedback.user.mgmt.port.mongo.entity.user.BusinessUnit;

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
