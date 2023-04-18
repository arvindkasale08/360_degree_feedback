package com.intuit.review.user.mgmt.port.http.mapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.review.user.mgmt.domain.bo.user.DesignationBo;
import com.intuit.review.user.mgmt.port.http.model.DesignationResponseDTO;
import ma.glasnost.orika.MapperFacade;

@Slf4j
@AllArgsConstructor
public class DesignationResponseDtoBoMapper {

	private MapperFacade mapperFacade;

	public DesignationResponseDTO mapBoToDto(DesignationBo bo) {
		return mapperFacade.map(bo, DesignationResponseDTO.class);
	}
}
