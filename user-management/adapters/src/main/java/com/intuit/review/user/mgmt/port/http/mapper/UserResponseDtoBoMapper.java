package com.intuit.review.user.mgmt.port.http.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.review.user.mgmt.domain.bo.user.UserBo;
import com.intuit.review.user.mgmt.port.http.model.UserResponseDTO;
import ma.glasnost.orika.MapperFacade;

@Slf4j
@RequiredArgsConstructor
public class UserResponseDtoBoMapper {

	private final MapperFacade mapperFacade;

	public UserResponseDTO mapBoToDto(UserBo bo) {
		return mapperFacade.map(bo, UserResponseDTO.class);
	}
}
