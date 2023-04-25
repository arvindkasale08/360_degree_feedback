package com.intuit.review.feedback.mgmt.service.port.kafka;

import java.util.Map;

import reactor.core.publisher.Mono;

public interface MessageSender<K, V> {

  String NOTIFICATION_REQUEST_KEY = "notificationRequest";

  Mono<Void> sendMessage(K key, Mono<V> message, Map<String, String> headers);
}
