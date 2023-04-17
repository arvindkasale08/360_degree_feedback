package com.intuit.review.feedback.mgmt.port.mongo.mapper;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackRequestBo;
import com.intuit.review.feedback.mgmt.port.mongo.entity.feedback.FeedbackRequest;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

@RequiredArgsConstructor
public class FeedbackRequestBoEntityMapper implements OrikaMapperFactoryConfigurer {

	private final MapperFacade mapperFacade;
	private final FeedbackUserBoEntityMapper feedbackUserBoEntityMapper;

	@Override
	public void configure(MapperFactory orikaMapperFactory) {
		orikaMapperFactory.classMap(FeedbackRequest.class, FeedbackRequestBo.class)
			.customize(new CustomMapper<FeedbackRequest, FeedbackRequestBo>() {
				@Override
				public void mapAtoB(FeedbackRequest entity, FeedbackRequestBo bo, MappingContext context) {
					//super.mapAtoB(entity, bo, context);
					if (entity.getSubject() != null) {
						bo.setSubject(feedbackUserBoEntityMapper.mapEntityToBo(entity.getSubject()));
					}
					if (entity.getActor() != null) {
						bo.setSubject(feedbackUserBoEntityMapper.mapEntityToBo(entity.getActor()));
					}
					if (entity.getRequestor() != null) {
						bo.setSubject(feedbackUserBoEntityMapper.mapEntityToBo(entity.getRequestor()));
					}
				}

				@Override
				public void mapBtoA(FeedbackRequestBo bo, FeedbackRequest entity, MappingContext context) {
					//super.mapBtoA(bo, entity, context);
					if (bo.getSubject() != null) {
						entity.setSubject(feedbackUserBoEntityMapper.mapBoToEntity(bo.getSubject()));
					}
					if (bo.getActor() != null) {
						entity.setSubject(feedbackUserBoEntityMapper.mapBoToEntity(bo.getActor()));
					}
					if (bo.getRequestor() != null) {
						entity.setSubject(feedbackUserBoEntityMapper.mapBoToEntity(bo.getRequestor()));
					}
				}
			})
			.byDefault()
			.register();
	}

	public FeedbackRequestBo mapEntityToBo(FeedbackRequest entity) {
		return mapperFacade.map(entity, FeedbackRequestBo.class);
	}

	public FeedbackRequest mapBoToEntity(FeedbackRequestBo bo) {
		return mapperFacade.map(bo, FeedbackRequest.class);
	}
}
