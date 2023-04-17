package com.intuit.review.feedback.mgmt.port.mongo.mapper;

import lombok.RequiredArgsConstructor;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackRequestBo;
import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackUserBo;
import com.intuit.review.feedback.mgmt.port.mongo.entity.feedback.FeedbackRequest;
import com.intuit.review.feedback.mgmt.port.mongo.entity.feedback.FeedbackUser;
import ma.glasnost.orika.MapperFacade;

@RequiredArgsConstructor
public class FeedbackUserBoEntityMapper {
	private final MapperFacade mapperFacade;

	public FeedbackUserBo mapEntityToBo(FeedbackUser entity) {
		return mapperFacade.map(entity, FeedbackUserBo.class);
	}

	public FeedbackUser mapBoToEntity(FeedbackUserBo bo) {
		return mapperFacade.map(bo, FeedbackUser.class);
	}
}
