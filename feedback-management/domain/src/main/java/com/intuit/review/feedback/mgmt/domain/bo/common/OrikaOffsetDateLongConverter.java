package com.intuit.review.feedback.mgmt.domain.bo.common;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class OrikaOffsetDateLongConverter extends BidirectionalConverter<Long, OffsetDateTime> {

	@Override
	public OffsetDateTime convertTo(Long source, Type<OffsetDateTime> destinationType,
																	MappingContext mappingContext) {
		return source != null ? LocalDateTime
			.ofInstant(Instant.ofEpochMilli(source),
				ZoneOffset.UTC).atOffset(ZoneOffset.UTC) : null;
	}

	@Override
	public Long convertFrom(OffsetDateTime source, Type<Long> destinationType,
													MappingContext mappingContext) {
		return source != null ? source.toInstant().toEpochMilli() : null;
	}
}
