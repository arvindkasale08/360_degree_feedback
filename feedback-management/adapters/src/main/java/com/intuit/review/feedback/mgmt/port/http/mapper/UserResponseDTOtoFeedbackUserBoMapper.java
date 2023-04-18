package com.intuit.review.feedback.mgmt.port.http.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackUserBo;
import com.intuit.review.feedback.mgmt.port.http.model.UserResponseDTO;
import ma.glasnost.orika.MapperFacade;

@Slf4j
@RequiredArgsConstructor
public class UserResponseDTOtoFeedbackUserBoMapper {

	private final MapperFacade mapperFacade;

	public FeedbackUserBo mapDtoToBo(UserResponseDTO dto) {
		return mapperFacade.map(dto, FeedbackUserBo.class);
	}
}
