package com.intuit.review.user.mgmt.port.mongo.mapper;

import com.intuit.review.user.mgmt.domain.bo.user.ProfileBo;
import com.intuit.review.user.mgmt.port.mongo.entity.user.Profile;
import ma.glasnost.orika.MapperFacade;

public class ProfileBoEntityMapper {

	private MapperFacade mapperFacade;

	public ProfileBo mapEntityToBo(Profile entity) {
		return mapperFacade.map(entity, ProfileBo.class);
	}

	public Profile mapBoToEntity(ProfileBo bo) {
		return mapperFacade.map(bo, Profile.class);
	}
}
