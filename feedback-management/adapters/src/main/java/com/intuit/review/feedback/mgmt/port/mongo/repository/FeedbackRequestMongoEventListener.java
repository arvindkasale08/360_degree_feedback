package com.intuit.review.feedback.mgmt.port.mongo.repository;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.intuit.review.feedback.mgmt.port.mongo.entity.feedback.FeedbackRequest;

@Component
@Slf4j
public class FeedbackRequestMongoEventListener extends AbstractMongoEventListener<FeedbackRequest> {

	@Override
	public void onBeforeConvert(BeforeConvertEvent<FeedbackRequest> event) {
		log.info("In BeforeConvert for Feedback Request");
		FeedbackRequest request = event.getSource();
		if (request.getCreatedDate() == null) {
			request.setCreatedDate(
				OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC).toInstant().toEpochMilli());
			log.debug("Populated created date for feedback request");
		}
		request.setUpdatedDate(
			OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC).toInstant().toEpochMilli()
		);
		log.debug("Populated updated date for feedback request");
	}
}
