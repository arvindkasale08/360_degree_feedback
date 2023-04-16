package com.intuit.feedback.user.mgmt.persistence.mapper;

import com.intuit.feedback.user.mgmt.domain.bo.user.DesignationBo;
import com.intuit.feedback.user.mgmt.persistence.entity.user.Designation;
import ma.glasnost.orika.MapperFacade;

public class DesignationBoEntityMapper {

	private MapperFacade mapperFacade;

	public DesignationBo mapEntityToBo(Designation designation) {
		return mapperFacade.map(designation, DesignationBo.class);
	}

	public Designation mapBoToEntity(DesignationBo designationBo) {
		return mapperFacade.map(designationBo, Designation.class);
	}
}
