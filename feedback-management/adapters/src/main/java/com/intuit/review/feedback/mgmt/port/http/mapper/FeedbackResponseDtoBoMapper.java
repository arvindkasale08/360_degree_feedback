package com.intuit.review.feedback.mgmt.port.http.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackBo;
import com.intuit.review.feedback.mgmt.port.http.model.FeedbackResponseDTO;
import ma.glasnost.orika.MapperFacade;

@Slf4j
@RequiredArgsConstructor
public class FeedbackResponseDtoBoMapper {

	private final MapperFacade mapperFacade;

	public FeedbackResponseDTO mapBoToDto(FeedbackBo bo) {
		return mapperFacade.map(bo, FeedbackResponseDTO.class);
	}
}
