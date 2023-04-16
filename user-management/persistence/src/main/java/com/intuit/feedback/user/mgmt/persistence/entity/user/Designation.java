package com.intuit.feedback.user.mgmt.persistence.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "designation")
public class Designation {
	private String id;
	private String name;
	private String code;
}
