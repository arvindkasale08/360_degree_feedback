package com.intuit.review.feedback.mgmt.port.mongo.mapper;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackRequestBo;
import com.intuit.review.feedback.mgmt.port.mongo.entity.feedback.FeedbackRequest;
import ma.glasnost.orika.MapperFacade;

public class FeedbackRequestBoEntityMapper {
	private MapperFacade mapperFacade;

	public FeedbackRequestBo mapEntityToBo(FeedbackRequest entity) {
		return mapperFacade.map(entity, FeedbackRequestBo.class);
	}

	public FeedbackRequest mapBoToEntity(FeedbackRequestBo bo) {
		return mapperFacade.map(bo, FeedbackRequest.class);
	}
}
