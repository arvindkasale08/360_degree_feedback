package com.intuit.review.feedback.mgmt.port.confluentKafka.configuration;

import static java.lang.Integer.min;
import static org.apache.kafka.clients.CommonClientConfigs.SECURITY_PROTOCOL_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.ACKS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.CLIENT_ID_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.RETRIES_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.common.config.SaslConfigs.SASL_JAAS_CONFIG;
import static org.apache.kafka.common.config.SaslConfigs.SASL_MECHANISM;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.util.StringUtils;

import com.intuit.review.feedback.mgmt.domain.exception.MissingKafkaOptionConfigurationException;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.SenderOptions;

@AllArgsConstructor
@Slf4j
public class ConfluentKafkaConfigurationOptionsProvider {

  private ConfluentKafkaProperties properties;

  public <K, V> ReceiverOptions<K, V> receiverOptions(String key) {

    Map<String, Object> receiverProps = new HashMap<>();
    ConfluentKafkaProperties.Bindings binding = properties.getBindings().get(key);
    if (binding == null) {
      throw missingKafkaOptions();
    }

    receiverProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());

    receiverProps.put(ConsumerConfig.GROUP_ID_CONFIG, binding.getGroupId());
    receiverProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
      binding.getKeyDeserilizer());
    receiverProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
      binding.getValueDeserilizer());

    // Config changes to slowdown kafka
    receiverProps.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, binding.getMaxPollRecords());
    receiverProps.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, binding.getFetchMaxBytes());
    receiverProps.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, binding.getMaxPartitionFetchBytes());
    receiverProps.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
    receiverProps.putAll(securityConfig());
    receiverProps.putAll(schemaRegistryConfig());

    return ReceiverOptions
      .<K, V>create(receiverProps)
      .commitInterval(Duration.ZERO)
      .addAssignListener(p -> log.info("{} consumer partitions assigned {}", key, p))
      .addRevokeListener(p -> log.info("{} consumer partitions revoked {}", key, p))
      .subscription(binding.getTopics());
  }

  public <K, V> KafkaReceiver<K, V> kafkaReceiver(String key) {
    return KafkaReceiver.create(receiverOptions(key));
  }

  public SenderOptions senderOptions(String key) {

    Map<String, Object> senderProps = new HashMap<>();
    ConfluentKafkaProperties.Options sender = properties.getOptions().get(key);
    if (sender == null) {
      throw missingKafkaOptions();
    }

    senderProps.put(BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
    senderProps.put(CLIENT_ID_CONFIG, sender.getClientId());
    senderProps.put(KEY_SERIALIZER_CLASS_CONFIG, sender.getKeySerializer());
    senderProps.put(VALUE_SERIALIZER_CLASS_CONFIG, sender.getValueSerializer());
    senderProps.put(ACKS_CONFIG, "all");
    senderProps.put(RETRIES_CONFIG, min(1, sender.getRetries()));
    senderProps.putAll(securityConfig());
    senderProps.putAll(schemaRegistryConfig());

    return SenderOptions.create(senderProps).stopOnError(false);
  }

  public <K, V> ProducerRecord<K, V> producerRecord(V value, K key,
                                                    Map<String, String> kafkaHeaders, String senderKey) {
    ConfluentKafkaProperties.Options sender = properties.getOptions().get(senderKey);
    if (sender == null) {
      throw missingKafkaOptions();
    }
    ProducerRecord<K, V> producerRecord = new ProducerRecord<>(sender.getTopic(), key, value);
    if (kafkaHeaders != null && !kafkaHeaders.isEmpty()) {
      kafkaHeaders.entrySet().stream()
        .forEach(e -> producerRecord.headers().add(e.getKey(), e.getValue().getBytes()));
    }
    return producerRecord;
  }

  @SuppressWarnings("Duplicates")
  private Map<String, Object> securityConfig() {
    Map<String, Object> securityProps = new HashMap<>();

    if (!StringUtils.isEmpty(properties.getSecurityProtocol())) {
      securityProps.put(SECURITY_PROTOCOL_CONFIG, properties.getSecurityProtocol());
    }

    ConfluentKafkaProperties.Sasl sasl = properties.getSasl();
    if (sasl != null) {
      securityProps.put(SASL_MECHANISM, sasl.getMechanism());

      StringBuilder jaasConfig = new StringBuilder();
      jaasConfig.append(sasl.getJaasConfig() + " required");
      jaasConfig.append(" username=\'" + properties.getUser() + "\'");
      jaasConfig.append(" password=\'" + properties.getPassword() + "\';");

      securityProps.put(SASL_JAAS_CONFIG, jaasConfig.toString());
    }

    return securityProps;
  }

  private Map<String, Object> schemaRegistryConfig() {
    Map<String, Object> schemaProps = new HashMap<>();
    if (properties.getSchemaRegistry() != null) {
      ConfluentKafkaProperties.SchemaRegistry schemaRegistryConfig = properties.getSchemaRegistry();
      // hardcoding for now
      schemaProps.put("basic.auth.user.info", "V65TAMFZ66CAJ4BQ:uLHkclsuiviGzNNmQBxn3PXBt1qAd5829XSecE4Fbx7K4F3SY9QrHIB2XluPzthy");
      schemaProps.put("basic.auth.credentials.source", "USER_INFO");
      schemaProps.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryConfig.getUrl());
      schemaProps
          .put(AbstractKafkaSchemaSerDeConfig.VALUE_SUBJECT_NAME_STRATEGY, schemaRegistryConfig.getNameStrategy());
      schemaProps.put(AbstractKafkaSchemaSerDeConfig.AUTO_REGISTER_SCHEMAS, schemaRegistryConfig.isAutoRegister());
      schemaProps.put(AbstractKafkaSchemaSerDeConfig.USE_LATEST_VERSION, schemaRegistryConfig.isUseLatestVersion());
    }
    return schemaProps;
  }

  private MissingKafkaOptionConfigurationException missingKafkaOptions() {
    return new MissingKafkaOptionConfigurationException("Missing kafka configuration."
                                                            .concat("Configure properties kafka.options.*"));
  }

}
