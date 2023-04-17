package com.intuit.review.feedback.mgmt.port.mongo.mapper;

import lombok.RequiredArgsConstructor;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackBo;
import com.intuit.review.feedback.mgmt.port.mongo.entity.feedback.Feedback;
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
		orikaMapperFactory.classMap(Feedback.class, FeedbackBo.class)
			.customize(new CustomMapper<Feedback, FeedbackBo>() {
				@Override
				public void mapAtoB(Feedback entity, FeedbackBo bo, MappingContext context) {
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
				public void mapBtoA(FeedbackBo bo, Feedback entity, MappingContext context) {
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

	public FeedbackBo mapEntityToBo(Feedback entity) {
		return mapperFacade.map(entity, FeedbackBo.class);
	}

	public Feedback mapBoToEntity(FeedbackBo bo) {
		return mapperFacade.map(bo, Feedback.class);
	}
}
