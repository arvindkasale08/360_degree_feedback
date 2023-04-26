package com.intuit.review.feedback.mgmt.http;

import com.intuit.review.feedback.mgmt.port.http.FeedbackHandler;
import com.intuit.review.feedback.mgmt.port.http.model.InitializeFeedbackRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = FeedbackController.class)
public class FeedbackController {

    @Autowired
    private WebTestClient webClient;

    @Test
    void testInitializeFeedback() {

        InitializeFeedbackRequestDTO requestDTO = new InitializeFeedbackRequestDTO();
        requestDTO.setActorId("ffed3dc5-e9df-407e-aebb-62e827153e6a");
        requestDTO.setSubjectId("fff6a5e1-d52a-4b79-9908-2e227f2a4261");
        requestDTO.setRequestorId("a267db4e-c5cc-4656-abda-03b130cb45c6");

        webClient.post()
                .uri("/api/review/v1/feedback/initialize")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(requestDTO))
                .exchange()
                .expectStatus().isCreated();

    }

}
