package com.intuit.review.feedback.mgmt.port.http.mapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackRequestLightBo;
import com.intuit.review.feedback.mgmt.port.http.model.FeedbackRequestRequestDTO;
import ma.glasnost.orika.MapperFacade;

@Slf4j
@AllArgsConstructor
public class FeedbackRequestRequestDtoLightBoMapper {

	private MapperFacade mapperFacade;

	public FeedbackRequestLightBo mapDtoToBo(FeedbackRequestRequestDTO dto) {
		return mapperFacade.map(dto, FeedbackRequestLightBo.class);
	}
}
