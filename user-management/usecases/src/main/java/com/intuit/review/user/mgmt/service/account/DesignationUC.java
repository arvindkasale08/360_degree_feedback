package com.intuit.review.user.mgmt.service.account;

import lombok.RequiredArgsConstructor;

import com.intuit.review.user.mgmt.domain.bo.user.DesignationBo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DesignationUC {

	public Mono<DesignationBo> createDesignation(DesignationBo designationBo) {
		return null;
	}

	public Mono<DesignationBo> updateDesignation(DesignationBo designationBo) {
		return null;
	}

	public Flux<DesignationBo> getAllDesignations() {
		return null;
	}

	public Mono<DesignationBo> getDesignation(String code) {
		return null;
	}
}
