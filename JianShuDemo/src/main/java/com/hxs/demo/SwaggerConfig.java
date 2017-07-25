package com.hxs.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RequestMapping("/hxs")
@RestController
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket demoApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.hxs.demo")).paths(regex("/hxs.*")).build()
				.apiInfo(metaData());

	}

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("Spring Boot REST API Demo by hxs", "Spring Boot REST API for Simple Demo", "1.0",
				"termsOfServiceUrl",
				new Contact("Xingsheng Han", "https://github.com/XiaoHanChina/SpringBootSwagger",
						"xingsheng_han@163.com"),
				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0");
		return apiInfo;
	}

	@RequestMapping("/") // Get any visitation by this route, no matter GET,
							// POST, DELETE, etc.
	public String hello() {
		System.out.println("Got one visitation.");
		return "Hello world!";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "This is the index.";
	}

	@ApiOperation(value = "View a list of available products", response = SimpleUserBean.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public Iterable<SimpleUserBean> list(Model model) {
		ArrayList<SimpleUserBean> list = new ArrayList<>();
		list.add(new SimpleUserBean(1, "hxs", "123456"));
		list.add(new SimpleUserBean(2, "han", "111111"));
		return list;
	}

}
