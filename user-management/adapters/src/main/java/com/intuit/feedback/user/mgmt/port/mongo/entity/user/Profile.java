package com.intuit.feedback.user.mgmt.port.mongo.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "profile")
public class Profile {

	private String email;
	private String businessUnitId;
	private String designationId;
	// reference to a user whose profile this is
	private String userId;
}
