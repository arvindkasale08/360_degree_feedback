package com.intuit.feedback.user.mgmt.persistence.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intuit.feedback.user.mgmt.domain.bo.common.OrikaOffsetDateLongConverter;
import com.intuit.feedback.user.mgmt.domain.bo.common.OrikaOffsetDateStringConverter;
import com.intuit.feedback.user.mgmt.persistence.mapper.BusinessUnitBoEntityMapper;
import com.intuit.feedback.user.mgmt.persistence.mapper.DesignationBoEntityMapper;
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
}
