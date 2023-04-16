package com.intuit.feedback.user.mgmt.persistence.entity.user;

import java.lang.annotation.Documented;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "business_unit")
public class BusinessUnit {
	private String id;
	private String name;
	private String code;
}
