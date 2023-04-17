package com.intuit.review.feedback.mgmt.port.http.mapper;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackRequestBo;
import com.intuit.review.feedback.mgmt.port.http.model.FeedbackRequestResponseDTO;
import ma.glasnost.orika.MapperFacade;

@Slf4j
@RequiredArgsConstructor
public class FeedbackRequestResponseDtoBoMapper {

	private final MapperFacade mapperFacade;

	public FeedbackRequestResponseDTO mapBoToDto(FeedbackRequestBo bo) {
		return mapperFacade.map(bo, FeedbackRequestResponseDTO.class);
	}
}
