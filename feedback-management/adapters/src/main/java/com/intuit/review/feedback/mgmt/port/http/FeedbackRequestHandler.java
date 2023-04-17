package com.intuit.review.feedback.mgmt.port.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.review.feedback.mgmt.port.http.mapper.FeedbackRequestRequestDtoLightBoMapper;
import com.intuit.review.feedback.mgmt.port.http.mapper.FeedbackRequestResponseDtoBoMapper;
import com.intuit.review.feedback.mgmt.port.http.model.FeedbackRequestRequestDTO;
import com.intuit.review.feedback.mgmt.port.http.model.FeedbackRequestResponseDTO;
import com.intuit.review.feedback.mgmt.service.feedback.FeedbackRequestUC;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class FeedbackRequestHandler {

	private final FeedbackRequestUC feedbackRequestUC;
	private final FeedbackRequestRequestDtoLightBoMapper requestDtoLightBoMapper;
	private final FeedbackRequestResponseDtoBoMapper responseDtoBoMapper;

	public Mono<FeedbackRequestResponseDTO> handleCreate(FeedbackRequestRequestDTO requestDTO) {
		log.info("FeedbackRequestHandler:: creating feedback with actorId={}, requestorId={}, subjectId={}", requestDTO.getActorId(), requestDTO.getRequestorId(), requestDTO.getSubjectId());

		return Mono.just(requestDtoLightBoMapper.mapDtoToBo(requestDTO))
			.flatMap(feedbackRequestUC::createFeedbackRequest)
			.map(responseDtoBoMapper::mapBoToDto);
	}

	public Mono<FeedbackRequestResponseDTO> handleGetById(String id) {
		log.info("FeedbackRequestHandler:: getting feedback for id={}", id);
		return feedbackRequestUC.getFeedbackRequestById(id)
			.map(responseDtoBoMapper::mapBoToDto);
	}
}
