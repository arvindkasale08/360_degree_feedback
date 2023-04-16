package com.intuit.feedback.user.mgmt.port.http.mapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.feedback.user.mgmt.domain.bo.user.DesignationBo;
import com.intuit.feedback.user.mgmt.port.api.model.DesignationRequestDTO;
import com.intuit.feedback.user.mgmt.port.api.model.DesignationResponseDTO;
import ma.glasnost.orika.MapperFacade;

@Slf4j
@AllArgsConstructor
public class DesignationResponseDtoBoMapper {

	private MapperFacade mapperFacade;

	public DesignationResponseDTO mapBoToDto(DesignationBo bo) {
		return mapperFacade.map(bo, DesignationResponseDTO.class);
	}
}
