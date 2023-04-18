package com.intuit.review.user.mgmt.port.http.mapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.review.user.mgmt.domain.bo.user.DesignationBo;
import com.intuit.review.user.mgmt.port.http.model.DesignationRequestDTO;
import ma.glasnost.orika.MapperFacade;

@Slf4j
@AllArgsConstructor
public class DesignationRequestDtoBoMapper {

	private MapperFacade mapperFacade;

	public DesignationBo mapDtoToBo(DesignationRequestDTO dto) {
		return mapperFacade.map(dto, DesignationBo.class);
	}
}
