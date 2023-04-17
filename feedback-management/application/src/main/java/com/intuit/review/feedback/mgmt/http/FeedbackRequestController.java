package com.intuit.review.feedback.mgmt.http;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.review.feedback.mgmt.port.http.FeedbackRequestHandler;
import com.intuit.review.feedback.mgmt.port.http.model.FeedbackRequestRequestDTO;
import com.intuit.review.feedback.mgmt.port.http.model.FeedbackRequestResponseDTO;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping(value = "/api/review/v1/feedbackRequests")
public class FeedbackRequestController {

	@Autowired
	private FeedbackRequestHandler feedbackRequestHandler;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Mono<ResponseEntity<FeedbackRequestResponseDTO>> createFeedbackRequest(@Valid @RequestBody FeedbackRequestRequestDTO request) {
		return feedbackRequestHandler.handleCreate(request)
			.map(feedbackRequestResponseDTO -> new ResponseEntity<>(feedbackRequestResponseDTO, HttpStatus.OK));
	}
}
