package com.intuit.feedback.user.mgmt.persistence.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.intuit.feedback.user.mgmt.persistence.entity.user.BusinessUnit;
import com.intuit.feedback.user.mgmt.persistence.entity.user.Designation;

public interface BusinessUnitMongoRepository extends ReactiveMongoRepository<BusinessUnit, String> {
}
