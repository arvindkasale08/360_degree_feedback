package com.intuit.review.feedback.mgmt.port.mongo.repository;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.intuit.review.feedback.mgmt.port.mongo.entity.feedback.Feedback;

@Component
@Slf4j
public class FeedbackMongoEventListener extends AbstractMongoEventListener<Feedback> {

	@Override
	public void onBeforeConvert(BeforeConvertEvent<Feedback> event) {
		log.info("In BeforeConvert for Feedback");
		Feedback request = event.getSource();
		if (request.getCreatedDate() == null) {
			request.setCreatedDate(
				OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC).toInstant().toEpochMilli());
			log.debug("Populated created date for feedback");
		}
		request.setUpdatedDate(
			OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC).toInstant().toEpochMilli()
		);
		log.debug("Populated updated date for feedback");
	}
}
