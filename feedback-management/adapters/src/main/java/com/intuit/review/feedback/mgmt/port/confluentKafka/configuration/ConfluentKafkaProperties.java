package com.intuit.review.feedback.mgmt.port.confluentKafka.configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "confluentkafka")
@Data
public class ConfluentKafkaProperties {

	private String bootstrapServers;
	private String securityProtocol;
	private String user;
	private String password;
	private Sasl sasl;
	private Map<String, Options> options;
	private Map<String, Bindings> bindings;
	private SchemaRegistry schemaRegistry;
	private boolean enabled;

	@Data
	public static class Bindings {

		private String topic;
		private String groupId;
		private Class keyDeserilizer;
		private Class valueDeserilizer;
		private String maxPollRecords;
		private String fetchMaxBytes;
		private String maxPartitionFetchBytes;

		public List<String> getTopics() {
			return Arrays.asList(topic.split(","));
		}
	}

	@Data
	public static class Options {

		private String topic;
		private String clientId;
		private int retries;
		private Class keySerializer;
		private Class valueSerializer;
	}

	@Data
	public static class Sasl {

		private String mechanism;
		private String jaasConfig;
	}

	@Data
	public static class SchemaRegistry {
		private String url;
		private boolean autoRegister;
		private boolean useLatestVersion;
		private Class nameStrategy;
	}
}
