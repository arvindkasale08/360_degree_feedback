package com.intuit.feedback.user.mgmt.persistence.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.intuit.feedback.user.mgmt.persistence.entity.user.Designation;

public interface DesignationMongoRepository extends ReactiveMongoRepository<Designation, String> {
}
