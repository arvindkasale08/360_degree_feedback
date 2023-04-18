package com.intuit.review.feedback.mgmt.port.http.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackBo;
import com.intuit.review.feedback.mgmt.domain.bo.feedback.FeedbackUserBo;
import com.intuit.review.feedback.mgmt.port.http.model.UserResponseDTO;
import com.intuit.review.feedback.mgmt.port.mongo.entity.feedback.Feedback;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

@Slf4j
@RequiredArgsConstructor
public class UserResponseDTOtoFeedbackUserBoMapper implements OrikaMapperFactoryConfigurer {

	private final MapperFacade mapperFacade;

	@Override
	public void configure(MapperFactory orikaMapperFactory) {
		orikaMapperFactory.classMap(UserResponseDTO.class, FeedbackUserBo.class)
			.customize(new CustomMapper<UserResponseDTO, FeedbackUserBo>() {
				@Override
				public void mapAtoB(UserResponseDTO dto, FeedbackUserBo bo, MappingContext context) {
					//super.mapAtoB(entity, bo, context);
					if (dto != null) {
						bo.setId(dto.getExternalId());
						if (dto.getProfile() != null) {
							bo.setEmail(dto.getProfile().getEmail());
							bo.setFirstName(dto.getProfile().getFirstName());
							bo.setLastName(dto.getProfile().getLastName());
						}
					}
				}

				@Override
				public void mapBtoA(FeedbackUserBo bo, UserResponseDTO entity, MappingContext context) {
					//super.mapBtoA(bo, entity, context);
					// Should not come here
				}
			})
			.byDefault()
			.register();
	}

	public FeedbackUserBo mapDtoToBo(UserResponseDTO dto) {
		return mapperFacade.map(dto, FeedbackUserBo.class);
	}
}
