package com.intuit.review.user.mgmt.port.mongo.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

	private String firstName;
	private String lastName;
	private String email;
	private String businessUnit;
	private String designation;
	private String imageUrl;
}
