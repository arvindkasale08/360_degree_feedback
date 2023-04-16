package com.intuit.feedback.user.mgmt.persistence.mapper;

import com.intuit.feedback.user.mgmt.domain.bo.user.BusinessUnitBo;
import com.intuit.feedback.user.mgmt.persistence.entity.user.BusinessUnit;
import ma.glasnost.orika.MapperFacade;

public class BusinessUnitBoEntityMapper {

	private MapperFacade mapperFacade;

	public BusinessUnitBo mapEntityToBo(BusinessUnit businessUnit) {
		return mapperFacade.map(businessUnit, BusinessUnitBo.class);
	}

	public BusinessUnit mapBoToEntity(BusinessUnitBo businessUnitBo) {
		return mapperFacade.map(businessUnitBo, BusinessUnit.class);
	}
}