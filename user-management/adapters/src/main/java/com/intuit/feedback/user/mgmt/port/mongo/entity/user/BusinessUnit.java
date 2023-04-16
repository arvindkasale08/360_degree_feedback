package com.intuit.feedback.user.mgmt.port.mongo.entity.user;

import java.lang.annotation.Documented;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "business_unit")
public class BusinessUnit {
	@Id
	private String id;
	private String name;
	@Indexed(unique = true)
	private String code;
}
