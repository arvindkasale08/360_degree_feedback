package com.intuit.review.feedback.mgmt.configuration;

import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class ResponseHeaderWebFilter implements WebFilter {

		@Override
		public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
			LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();

			HttpMethod method = exchange.getRequest().getMethod();
			if (method != null) {
				exchange.getResponse()
					.getHeaders()
					.add("Access-Control-Allow-Methods", method.name());
				exchange.getResponse()
					.getHeaders()
					.add("Access-Control-Allow-Headers", "*");
				exchange.getResponse()
					.getHeaders()
					.add("Access-Control-Allow-Origin", "*");
			}
			return chain.filter(exchange);
		}
}
