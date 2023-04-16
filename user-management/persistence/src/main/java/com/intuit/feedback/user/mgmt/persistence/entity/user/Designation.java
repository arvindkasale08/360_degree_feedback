package com.intuit.feedback.user.mgmt.persistence.entity.user;

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
@Document(collection = "designation")
public class Designation {
	@Id
	private String id;
	private String name;
	@Indexed(unique = true)
	private String code;
}
