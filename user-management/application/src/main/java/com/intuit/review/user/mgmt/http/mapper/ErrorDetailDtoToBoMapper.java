package com.intuit.review.user.mgmt.http.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.intuit.review.user.mgmt.domain.bo.common.ErrorDetailBo;
import com.intuit.review.user.mgmt.port.http.model.ErrorDetailDTO;
import ma.glasnost.orika.MapperFacade;

@Component
public class ErrorDetailDtoToBoMapper {

	@Lazy
	@Autowired
	private MapperFacade mapperFacade;

	public ErrorDetailBo mapDtoToBo(ErrorDetailDTO errorDetailDto) {
		return mapperFacade.map(errorDetailDto, ErrorDetailBo.class);
	}

	public ErrorDetailDTO mapBoToDto(ErrorDetailBo errorDetailBo) {
		return mapperFacade.map(errorDetailBo, ErrorDetailDTO.class);
	}

	public List<ErrorDetailDTO> mapBoToDtoList(List<ErrorDetailBo> list) {
		return mapperFacade.mapAsList(list, ErrorDetailDTO.class);
	}
}
