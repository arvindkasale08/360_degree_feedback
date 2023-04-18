package com.intuit.review.feedback.mgmt.port.http.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackLightBo;
import com.intuit.review.feedback.mgmt.port.http.model.FinalizeFeedbackRequestDTO;
import com.intuit.review.feedback.mgmt.port.http.model.InitializeFeedbackRequestDTO;
import ma.glasnost.orika.MapperFacade;

@Slf4j
@RequiredArgsConstructor
public class FinalizeFeedbackRequestLightBoMapper {

	private final MapperFacade mapperFacade;

	public FeedbackLightBo mapDtoToBo(FinalizeFeedbackRequestDTO dto) {
		return mapperFacade.map(dto, FeedbackLightBo.class);
	}
}
