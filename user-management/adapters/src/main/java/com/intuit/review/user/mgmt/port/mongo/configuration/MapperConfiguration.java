package com.intuit.review.user.mgmt.port.mongo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intuit.review.user.mgmt.domain.bo.common.OrikaOffsetDateLongConverter;
import com.intuit.review.user.mgmt.domain.bo.common.OrikaOffsetDateStringConverter;
import com.intuit.review.user.mgmt.domain.bo.user.UserBo;
import com.intuit.review.user.mgmt.port.mongo.mapper.BusinessUnitBoEntityMapper;
import com.intuit.review.user.mgmt.port.mongo.mapper.DesignationBoEntityMapper;
import com.intuit.review.user.mgmt.port.mongo.mapper.ProfileBoEntityMapper;
import com.intuit.review.user.mgmt.port.mongo.mapper.UserBoEntityMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Configuration
public class MapperConfiguration {

	@Bean
	public MapperFactory mapperFactory() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.getConverterFactory().registerConverter(new OrikaOffsetDateLongConverter());
		mapperFactory.getConverterFactory().registerConverter(new OrikaOffsetDateStringConverter());
		return mapperFactory;
	}

	@Bean
	public MapperFacade mapperFacade(MapperFactory mapperFactory) {
		return mapperFactory.getMapperFacade();
	}

	@Bean
	public BusinessUnitBoEntityMapper businessUnitBoEntityMapper(MapperFacade mapperFacade, MapperFactory mapperFactory) {
		BusinessUnitBoEntityMapper mapper = new BusinessUnitBoEntityMapper();
		return mapper;
	}

	@Bean
	public DesignationBoEntityMapper designationBoEntityMapper(MapperFacade mapperFacade, MapperFactory mapperFactory) {
		DesignationBoEntityMapper mapper = new DesignationBoEntityMapper();
		return mapper;
	}

	@Bean
	public UserBoEntityMapper userBoEntityMapper(MapperFacade mapperFacade, MapperFactory mapperFactory) {
		UserBoEntityMapper mapper = new UserBoEntityMapper(mapperFacade);
		return mapper;
	}

	@Bean
	public ProfileBoEntityMapper profileBoEntityMapper(MapperFacade mapperFacade, MapperFactory mapperFactory) {
		ProfileBoEntityMapper mapper = new ProfileBoEntityMapper();
		return mapper;
	}
}
