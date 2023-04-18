package com.intuit.review.feedback.mgmt.configuration;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.intuit.review.feedback.mgmt.port.http.client.UserServiceImpl;
import com.intuit.review.feedback.mgmt.service.port.http.UserService;

@Slf4j
@Configuration
public class ApiServiceConfig {


	// Client Configurations
	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}

	@Bean
	@Scope("prototype")
	@Primary
	public WebClient.Builder webClientBuilder(final ObjectProvider<WebClientCustomizer> customizerProvider) {
		final WebClient.Builder builder = WebClient.builder();
		// can do custom orchestration / fitlerring here
		/*// remove default MetricsWebClientCustomizer
		customizerProvider.orderedStream()
			.forEach((customizer) ->
				customizer.customize(builder));
		// register custom MetricsWebClientCustomizer
		customMetricsWebClientCustomizer.customize(builder);
		builder
			.filter(logRequest())
			.filter(logResponse());*/
		return builder;
	}

	@Bean
	public WebClient userWebClient(WebClient.Builder webClientBuilder, @Value("${usermgmt.baseurl}") String url) {
		log.info("Creating userWebClient");
		return webClient(webClientBuilder, url);
	}

	private WebClient webClient(WebClient.Builder webClientBuilder, String url) {
		log.info("Configuring webclient using url={}", url);
		return webClientBuilder
			.baseUrl(url)
			.defaultHeaders(httpHeaders -> {
				httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
				httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
			})
			.build();
	}
}
