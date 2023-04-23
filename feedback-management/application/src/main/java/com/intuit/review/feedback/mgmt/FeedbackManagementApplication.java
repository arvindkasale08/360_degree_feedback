package com.intuit.review.feedback.mgmt;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.reactive.result.method.RequestMappingInfo;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = {"com.intuit.review.feedback.mgmt", "com.intuit.review.feedback.mgmt.port.mongo"})
public class FeedbackManagementApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(FeedbackManagementApplication.class, args);
		RequestMappingHandlerMapping requestMappingHandlerMapping = ctx
			.getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping
			.getHandlerMethods();
		map.forEach((key, value) -> log.info("{} {}", key, value));
	}

}
