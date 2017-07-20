###简介
本文主要介绍将以上三者组合，搭建简单的web服务器，并导出Human Readable的Restful API.
###一、Eclipse
只要搭建好平时的Eclipse、Java的开发环境即可。
###二、Spring Boot
#####搭建Spring Boot环境
 在eclipse中，Help - Eclipse Marketplace. 搜索Spring Tools,安装即可.

![image.png](http://upload-images.jianshu.io/upload_images/6046985-a3ad5aad0d88b32c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#####创建SpringBoot项目 [Quick Start](https://projects.spring.io/spring-boot/#quick-start)
New Project - Spring - Spring Started Project, 然后根据步骤填写包名等信息，Dependencies选择Web（带有Tomcat的Spring MVC项目）即可。
![image.png](http://upload-images.jianshu.io/upload_images/6046985-d93368285ebc8141.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
#####编写简单的接口
在Application中添加Hello world接口，在对应的方法上加上`RequestMapping`的注解，在Application上添加`RestController`的注解。（这里在Application上添加`@RequestMapping("/hxs")`表明本类的所有方法都在/hxs/路径下）
```
@RequestMapping("/hxs")
@RestController
@SpringBootApplication
public class JianShuDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JianShuDemoApplication.class, args);
	}

	@RequestMapping("/")//Get any visitation by this route, no matter GET, POST, DELETE, etc.
	public String hello() {
		System.out.println("Got one visitation.");
		return "Hello world!";
	}

}
```
#####测试SpringBoot
在Application上，右击，Run As - Spring Boot App.它就运行在本机的8080端口上，测试地址[http://localhost:8080/hxs/](http://localhost:8080/hxs/)，同时你的Eclipse的 控制台也会输出`Got one visitation.`
###三、添加Swagger到项目中 [More](https://springframework.guru/spring-boot-restful-api-documentation-with-swagger-2/)
#####pom.xml中添加Springfox依赖
```
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.6.1</version>
    <scope>compile</scope>
</dependency>
```
#####pom.xml中添加SwaggerUI依赖
```
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.6.1</version>
    <scope>compile</scope>
</dependency>
```
###配置Swagger，在Application中添加Dockey
Application添加`@EnableSwagger2`注解，类种添加`Dockey Bean`。（`regex`方法不能通过自动导包导入，需要添加静态依赖）
```
import static springfox.documentation.builders.PathSelectors.regex;
···
@RequestMapping("/hxs")
@RestController
@EnableSwagger2
@SpringBootApplication
public class JianShuDemoApplication {
    ...
	@Bean
	public Docket demoApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.hxs.demo"))
				.paths(regex("/hxs.*")).build();

	}

}

```
#####获取Swagger json
直接访问默认地址[http://localhost:8080/v2/api-docs](http://localhost:8080/v2/api-docs)
#####获取SwaggerUI
访问默认地址[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html), 点击`jian-shu-demo-application`即可展开看到所有的API地址，并可以测试

![image.png](http://upload-images.jianshu.io/upload_images/6046985-a95536bc2a3c8732.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
###四、进阶篇，接口注释及复杂API
