package com.intuit.review.feedback.mgmt.port.confluentKafka.mapper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackBo;
import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackStatus;
import com.intuit.review.feedback.mgmt.port.avro.FeedbackNotification;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

@Slf4j
@AllArgsConstructor
public class FeedbackBoAvroMapper implements OrikaMapperFactoryConfigurer {

	public static final String FEEDBACK = "FEEDBACK";
	private MapperFacade mapperFacade;
	public static final String FEEDBACK_NOTIFICATION = "FEEDBACK_NOTIFICATION";

	@Override
	public void configure(MapperFactory orikaMapperFactory) {
		orikaMapperFactory.classMap(FeedbackBo.class, FeedbackNotification.class)
			.exclude("metadata")
			.customize(new CustomMapper<FeedbackBo, FeedbackNotification>() {
				@Override
				public void mapAtoB(FeedbackBo bo, FeedbackNotification event, MappingContext context) {
					super.mapAtoB(bo, event, context);
					event.setObjectId(bo.getId());
					event.setObjectType(FEEDBACK);
					event.setEventMetadata(EventMetadataUtils.getEventMetadata(FEEDBACK_NOTIFICATION, bo.getStatus().name()));
				}

				@Override
				public void mapBtoA(FeedbackNotification event, FeedbackBo bo, MappingContext context) {
					// should never come here
				}
			})
			.byDefault()
			.register();
	}

	public FeedbackNotification mapBoToEvent(FeedbackBo bo) {
		return mapperFacade.map(bo, FeedbackNotification.class);
	}
}
