package com.intuit.review.feedback.mgmt.http;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.review.feedback.mgmt.port.http.FeedbackHandler;
import com.intuit.review.feedback.mgmt.port.http.model.FeedbackListResponseDTO;
import com.intuit.review.feedback.mgmt.port.http.model.FeedbackResponseDTO;
import com.intuit.review.feedback.mgmt.port.http.model.InitializeFeedbackRequestDTO;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping(value = "/api/review/v1/feedback/")
public class FeedbackController {

	@Autowired
	private FeedbackHandler feedbackHandler;
	private static final Integer DEFAULT_PAGE_SIZE = 10;
	private static final Integer MAX_PAGE_SIZE = 50;

	@RequestMapping(value = "initialize", method = RequestMethod.POST)
	public Mono<ResponseEntity<FeedbackResponseDTO>> createFeedbackRequest(@Valid @RequestBody InitializeFeedbackRequestDTO request) {
		return feedbackHandler.handleInitialize(request)
			.map(feedbackRequestResponseDTO -> new ResponseEntity<>(feedbackRequestResponseDTO, HttpStatus.OK));
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Mono<ResponseEntity<FeedbackResponseDTO>> getFeedbackById(@Valid @PathVariable("id") String id) {
		return feedbackHandler.handleGetById(id)
			.map(feedbackRequestResponseDTO -> new ResponseEntity<>(feedbackRequestResponseDTO, HttpStatus.OK));
	}

	@RequestMapping(value = "assignedToActor/{id}", method = RequestMethod.GET)
	public Mono<ResponseEntity<FeedbackListResponseDTO>> assignedToActor(@Valid @PathVariable("id") String id, @RequestParam(value = "page", required = true, defaultValue = "1") Integer page, @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
		Integer pageSize = getPageSize(size);
		return feedbackHandler.getInitializedFeedbacksForActor(id, page, pageSize)
			.map(responseDto -> new ResponseEntity<>(responseDto, HttpStatus.OK));
	}

	@RequestMapping(value = "me/{id}", method = RequestMethod.GET)
	public Mono<ResponseEntity<FeedbackListResponseDTO>> getMyFeedbacks(@Valid @PathVariable("id") String id, @RequestParam(value = "page", required = true, defaultValue = "1") Integer page, @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
		Integer pageSize = getPageSize(size);
		return feedbackHandler.getMyFeedbacks(id, page, pageSize)
			.map(responseDto -> new ResponseEntity<>(responseDto, HttpStatus.OK));
	}

	@RequestMapping(value = "directReporting/{id}", method = RequestMethod.GET)
	public Mono<ResponseEntity<FeedbackListResponseDTO>> getMyReportingsFeedbacks(@Valid @PathVariable("id") String id, @RequestParam(value = "page", required = true, defaultValue = "1") Integer page, @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
		Integer pageSize = getPageSize(size);
		return feedbackHandler.getDirectReportingFeedback(id, page, pageSize)
			.map(responseDto -> new ResponseEntity<>(responseDto, HttpStatus.OK));
	}

	private Integer getPageSize(Integer size) {
		if (size <= 0) {
			return DEFAULT_PAGE_SIZE;
		} else if (size > MAX_PAGE_SIZE) {
			return MAX_PAGE_SIZE;
		}
		return size;
	}
}
