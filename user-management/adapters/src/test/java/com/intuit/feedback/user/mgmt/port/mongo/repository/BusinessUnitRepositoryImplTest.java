package com.intuit.feedback.user.mgmt.port.mongo.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.intuit.feedback.user.mgmt.domain.bo.user.BusinessUnitBo;
import com.intuit.feedback.user.mgmt.domain.utils.DataSetCreator;
import com.intuit.feedback.user.mgmt.port.mongo.entity.user.BusinessUnit;
import com.intuit.feedback.user.mgmt.port.mongo.mapper.BusinessUnitBoEntityMapper;
import com.intuit.feedback.user.mgmt.port.utils.DBDatasetCreator;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class BusinessUnitRepositoryImplTest {

	@Mock
	private BusinessUnitMongoRepository repository = Mockito.mock(BusinessUnitMongoRepository.class);
	@Mock
	private BusinessUnitBoEntityMapper mapper = Mockito.mock(BusinessUnitBoEntityMapper.class);
	@InjectMocks
	private BusinessUnitRepositoryImpl businessUnitRepository;

	@BeforeAll
	static void setup() {
	}

	@Test
	void createBusinessUnit_valid() {
		// given
		BusinessUnitBo businessUnitBo = DataSetCreator.getBusinessUnitBo("1", "Accounts", "TEAM_ACCOUNTS");
		BusinessUnit businessUnit = DBDatasetCreator.getBusinessUnit("1", "Accounts", "TEAM_ACCOUNTS");
		Mockito.when(repository.insert(Mockito.any(BusinessUnit.class))).thenReturn(Mono.just(businessUnit));
		Mockito.when(mapper.mapBoToEntity(Mockito.any())).thenReturn(businessUnit);

		// when
		BusinessUnitBo result = businessUnitRepository.createBusinessUnit(businessUnitBo).block();

		// then
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getCode(), "TEAM_ACCOUNTS");
	}

	@Test
	void updateBusinessUnit() {
		// given

		// when

		// then
	}

	@Test
	void readBusinessUnit() {
		// given

		// when

		// then
	}

	@Test
	void readBusinessUnits() {
		// given

		// when

		// then
	}

	@Test
	void readBusinessUnitByCode() {
		// given

		// when

		// then
	}
}
