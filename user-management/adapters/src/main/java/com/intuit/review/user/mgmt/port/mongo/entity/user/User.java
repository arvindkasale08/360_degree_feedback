package com.intuit.review.user.mgmt.port.mongo.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.intuit.review.user.mgmt.domain.bo.common.UserStatus;
import com.intuit.review.user.mgmt.port.mongo.entity.common.AuditableEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
@EqualsAndHashCode(callSuper=false)
public class User extends AuditableEntity {
	@Id
	private String id;
	@Indexed(unique = true)
	private String externalId;
	private String username;
	private String managerId;
	private Profile profile;
	private UserStatus status;
}
