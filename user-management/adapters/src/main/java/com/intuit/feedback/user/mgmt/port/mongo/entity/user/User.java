package com.intuit.feedback.user.mgmt.port.mongo.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

import com.intuit.feedback.user.mgmt.domain.bo.common.UserStatus;
import com.intuit.feedback.user.mgmt.port.mongo.entity.common.AuditableEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
@EqualsAndHashCode(callSuper=false)
public class User extends AuditableEntity {

	private String id;
	private String username;
	private String password;
	private String managerId;
	private String managerName;
	private UserStatus status;
}
