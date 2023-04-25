package com.intuit.review.feedback.mgmt.configuration;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.stereotype.Component;

import reactor.kafka.sender.SenderOptions;
import reactor.kafka.sender.internals.ProducerFactory;

@Component
public class ReactorProducerFactory extends ProducerFactory {

  @Override
  public <K, V> Producer<K, V> createProducer(SenderOptions<K, V> senderOptions) {
    KafkaProducer kafkaProducer = new KafkaProducer<>(senderOptions.producerProperties(),
        senderOptions.keySerializer(),
        senderOptions.valueSerializer());
    return kafkaProducer;
  }


}
