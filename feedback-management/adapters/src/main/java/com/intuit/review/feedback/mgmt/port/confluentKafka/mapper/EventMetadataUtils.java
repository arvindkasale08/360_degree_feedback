package com.intuit.review.feedback.mgmt.port.confluentKafka.mapper;

import java.time.LocalDateTime;
import java.util.UUID;

import com.intuit.review.feedback.mgmt.port.avro.EventMetadata;

public class EventMetadataUtils {


	public static EventMetadata getEventMetadata(String type) {
		EventMetadata metadata = new EventMetadata();
		metadata.setId(UUID.randomUUID().toString());
		metadata.setTimestamp(LocalDateTime.now().toString());
		metadata.setOperation(null);
		metadata.setType(type);

		return metadata;
	}
}
