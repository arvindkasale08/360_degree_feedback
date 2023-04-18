package com.intuit.review.user.mgmt.port.mongo.repository;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.intuit.review.user.mgmt.port.mongo.entity.user.User;

@Component
@Slf4j
public class UserMongoEventListener extends AbstractMongoEventListener<User> {

	@Override
	public void onBeforeConvert(BeforeConvertEvent<User> event) {
		log.info("In BeforeConvert for User");
		User request = event.getSource();
		if (request.getCreatedDate() == null) {
			request.setCreatedDate(
				OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC).toInstant().toEpochMilli());
			log.debug("Populated created date for User");
		}
		request.setUpdatedDate(
			OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC).toInstant().toEpochMilli()
		);
		log.debug("Populated updated date for User");
	}
}
