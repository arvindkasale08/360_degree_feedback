package com.intuit.feedback.user.mgmt.port.mongo.mapper;

import com.intuit.feedback.user.mgmt.domain.bo.user.BusinessUnitBo;
import com.intuit.feedback.user.mgmt.port.mongo.entity.user.BusinessUnit;
import ma.glasnost.orika.MapperFacade;

public class BusinessUnitBoEntityMapper {

	private MapperFacade mapperFacade;

	public BusinessUnitBo mapEntityToBo(BusinessUnit entity) {
		return mapperFacade.map(entity, BusinessUnitBo.class);
	}

	public BusinessUnit mapBoToEntity(BusinessUnitBo bo) {
		return mapperFacade.map(bo, BusinessUnit.class);
	}
}
