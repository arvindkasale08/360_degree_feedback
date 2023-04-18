package com.intuit.review.user.mgmt;

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
@ComponentScan(basePackages = {"com.intuit.review.user.mgmt", "com.intuit.review.user.mgmt.port.mongo"})
public class UserManagementApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(UserManagementApplication.class, args);
		RequestMappingHandlerMapping requestMappingHandlerMapping = ctx
			.getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping
			.getHandlerMethods();
		map.forEach((key, value) -> log.info("{} {}", key, value));
	}

}
