package com.hxs.demo;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RequestMapping("/hxs")
@RestController
@EnableSwagger2
@SpringBootApplication
public class JianShuDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JianShuDemoApplication.class, args);
	}

	@RequestMapping("/") // Get any visitation by this route, no matter GET, POST, DELETE, etc.
	public String hello() {
		System.out.println("Got one visitation.");
		return "Hello world!";
	}

	@Bean
	public Docket demoApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.hxs.demo"))
				.paths(regex("/hxs.*")).build();

	}

}
