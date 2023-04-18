package com.intuit.review.user.mgmt.http.mapper;

import java.util.function.Function;

import lombok.Getter;

import org.springframework.stereotype.Component;

import com.intuit.review.user.mgmt.domain.bo.common.ErrorBo;
import com.intuit.review.user.mgmt.port.http.model.ErrorDTO;
import jakarta.annotation.PostConstruct;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Getter
@Component
public class ErrorDtoBoMapper {

	private final MapperFactory boToDtoMapperFactory = new DefaultMapperFactory.Builder().build();
	private Function<ErrorBo, ErrorDTO> boToDtoMapper = bo ->
			boToDtoMapperFactory.getMapperFacade().map(bo, ErrorDTO.class);

	@PostConstruct
	protected void configure() {
		boToDtoMapperFactory.classMap(ErrorBo.class, ErrorDTO.class)
				.byDefault()
				.register();
	}
}
