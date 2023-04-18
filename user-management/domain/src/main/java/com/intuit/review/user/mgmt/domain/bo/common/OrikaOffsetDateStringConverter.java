package com.intuit.review.user.mgmt.domain.bo.common;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.util.StringUtils;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class OrikaOffsetDateStringConverter extends BidirectionalConverter<String, OffsetDateTime> {

	@Override
	public OffsetDateTime convertTo(String source, Type<OffsetDateTime> destinationType,
																	MappingContext mappingContext) {
		return !StringUtils.isEmpty(source) ? OffsetDateTime.parse(source,
			DateTimeFormatter.ISO_OFFSET_DATE_TIME) : null;
	}

	@Override
	public String convertFrom(OffsetDateTime source, Type<String> destinationType,
													MappingContext mappingContext) {
		return source != null ? source.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME) : null;
	}
}
