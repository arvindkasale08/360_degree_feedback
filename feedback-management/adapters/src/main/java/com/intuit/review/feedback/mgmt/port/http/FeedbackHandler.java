package com.intuit.review.feedback.mgmt.port.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.intuit.review.feedback.mgmt.port.http.mapper.FinalizeFeedbackRequestLightBoMapper;
import com.intuit.review.feedback.mgmt.port.http.mapper.InitializeFeedbackRequestLightBoMapper;
import com.intuit.review.feedback.mgmt.port.http.mapper.FeedbackResponseDtoBoMapper;
import com.intuit.review.feedback.mgmt.port.http.model.FeedbackListResponseDTO;
import com.intuit.review.feedback.mgmt.port.http.model.FinalizeFeedbackRequestDTO;
import com.intuit.review.feedback.mgmt.port.http.model.InitializeFeedbackRequestDTO;
import com.intuit.review.feedback.mgmt.port.http.model.FeedbackResponseDTO;
import com.intuit.review.feedback.mgmt.port.http.model.PaginationInfoDTO;
import com.intuit.review.feedback.mgmt.service.feedback.FeedbackUC;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class FeedbackHandler {

	private final FeedbackUC feedbackUC;
	private final InitializeFeedbackRequestLightBoMapper initializeFeedbackRequestLightBoMapper;
	private final FinalizeFeedbackRequestLightBoMapper finalizeFeedbackRequestLightBoMapper;
	private final FeedbackResponseDtoBoMapper responseDtoBoMapper;

	public Mono<FeedbackResponseDTO> handleInitialize(InitializeFeedbackRequestDTO requestDTO) {
		log.info("FeedbackRequestHandler:: initializing feedback with actorId={}, requestorId={}, subjectId={}", requestDTO.getActorId(), requestDTO.getRequestorId(), requestDTO.getSubjectId());

		return Mono.just(initializeFeedbackRequestLightBoMapper.mapDtoToBo(requestDTO))
			.flatMap(feedbackUC::initializeFeedback)
			.map(responseDtoBoMapper::mapBoToDto);
	}

	public Mono<FeedbackResponseDTO> handleFinalize(String feedbackId, FinalizeFeedbackRequestDTO requestDTO) {
		log.info("FeedbackRequestHandler:: finalizing feedback");

		return Mono.just(finalizeFeedbackRequestLightBoMapper.mapDtoToBo(requestDTO))
			.flatMap(lightBo -> feedbackUC.finalizeFeedback(feedbackId, lightBo))
			.map(responseDtoBoMapper::mapBoToDto);
	}

	public Mono<FeedbackResponseDTO> handleGetById(String id) {
		log.info("FeedbackRequestHandler:: getting feedback for id={}", id);
		return feedbackUC.getFeedbackRequestById(id)
			.map(responseDtoBoMapper::mapBoToDto);
	}

	public Mono<FeedbackListResponseDTO> getInitializedFeedbacksForActor(String actorId, int page, int pageSize) {
		return feedbackUC.getInitializedFeedbacksForActor(actorId, page, pageSize)
			.map(responseDtoBoMapper::mapBoToDto)
			.collectList()
			// merge the result together
			.zipWith(feedbackUC.getCountOfInitializedFeedbacksForActor(actorId), (feedbacks, count) -> {
					PaginationInfoDTO pageInfo = new PaginationInfoDTO();
					pageInfo.currentPage(page);
					pageInfo.size(count.intValue());
					pageInfo.totalPages((int) Math.ceil((double) count / pageSize));
					FeedbackListResponseDTO res = new FeedbackListResponseDTO();
					res.feedbacks(feedbacks);
					res.paginationInfo(pageInfo);
					return res;
				}
			);
	}

	public Mono<FeedbackListResponseDTO> getMyFeedbacks(String subjectId, int page, int pageSize) {
		return feedbackUC.getMyFeedbacks(subjectId, page, pageSize)
			.map(responseDtoBoMapper::mapBoToDto)
			.collectList()
			// merge the result together
			.zipWith(feedbackUC.getCountOfMyFeedbacks(subjectId), (feedbacks, count) -> {
					PaginationInfoDTO pageInfo = new PaginationInfoDTO();
					pageInfo.currentPage(page);
					pageInfo.size(count.intValue());
					pageInfo.totalPages((int) Math.ceil((double) count / pageSize));
					FeedbackListResponseDTO res = new FeedbackListResponseDTO();
					res.feedbacks(feedbacks);
					res.paginationInfo(pageInfo);
					return res;
				}
			);
	}

	public Mono<FeedbackListResponseDTO> getDirectReportingFeedback(String managerId, int page, int pageSize) {
		return feedbackUC.getDirectReportingFeedback(managerId, page, pageSize)
			.map(responseDtoBoMapper::mapBoToDto)
			.collectList()
			// merge the result together
			.zipWith(feedbackUC.getCountOfDirectReportingFeedback(managerId), (feedbacks, count) -> {
					PaginationInfoDTO pageInfo = new PaginationInfoDTO();
					pageInfo.currentPage(page);
					pageInfo.size(count.intValue());
					pageInfo.totalPages((int) Math.ceil((double) count / pageSize));
					FeedbackListResponseDTO res = new FeedbackListResponseDTO();
					res.feedbacks(feedbacks);
					res.paginationInfo(pageInfo);
					return res;
				}
			);
	}
}
