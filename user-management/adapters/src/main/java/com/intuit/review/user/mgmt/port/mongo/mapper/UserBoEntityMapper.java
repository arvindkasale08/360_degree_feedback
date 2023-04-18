package com.intuit.review.user.mgmt.port.mongo.mapper;

import com.intuit.review.user.mgmt.domain.bo.user.UserBo;
import com.intuit.review.user.mgmt.port.mongo.entity.user.User;
import ma.glasnost.orika.MapperFacade;

public class UserBoEntityMapper {

	private MapperFacade mapperFacade;

	public UserBo mapEntityToBo(User entity) {
		return mapperFacade.map(entity, UserBo.class);
	}

	public User mapBoToEntity(UserBo bo) {
		return mapperFacade.map(bo, User.class);
	}
}
