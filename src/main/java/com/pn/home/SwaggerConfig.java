package com.pn.home;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.pn.home.constants.AppConstants;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile({ "dev", "test", "qa" })
public class SwaggerConfig {
	// http://localhost:8080/swagger-ui.html http://localhost:8080/v2/api-docs
	@Value("${spring.profiles.active}")
	private String env;

	@Bean
	public Docket api() {
		// helper header token
		final String swaggerToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJwYXNzd29yZFwiOlwiJDJhJDEwJC4wcjkySTVHekZFMDZ0VlFZWGJBZnVBQXNVd1dsa2pweG5VeDV0dmlzWGxvVFh5VHd0TWZtXCIsXCJ1c2VybmFtZVwiOlwicG5heWxvclwiLFwiYXV0aG9yaXRpZXNcIjpbe1wiYXV0aG9yaXR5XCI6XCJST0xFX0FETUlOXCJ9XSxcImFjY291bnROb25FeHBpcmVkXCI6dHJ1ZSxcImFjY291bnROb25Mb2NrZWRcIjp0cnVlLFwiY3JlZGVudGlhbHNOb25FeHBpcmVkXCI6dHJ1ZSxcImVuYWJsZWRcIjp0cnVlfSIsImV4cCI6MTY5NDg0ODg5NSwiaWF0IjoxNTY4NzA0ODk1fQ.wRO-CFMgGAe69m10e-n5CJNo_EP0WL_qse_eN8g2dn8TYXLqTtMf4XG3YZ2fxJXOIV0Vg-JlUvVGpHJs_jS3HA";

		if (env.equals("dev")) {
			return new Docket(DocumentationType.SWAGGER_2)
					.globalOperationParameters(Collections.singletonList(new ParameterBuilder()
							.name(AppConstants.AUTHORISATION_HEADER).modelRef(new ModelRef(AppConstants.MODAL_REF))
							.parameterType(AppConstants.HEADER).required(true).hidden(true)
							.defaultValue(AppConstants.BEARER + swaggerToken).build()))
					.globalOperationParameters(Collections.singletonList(
							new ParameterBuilder().name("headers").modelRef(new ModelRef(AppConstants.MODAL_REF))
									.parameterType("headers").required(true).hidden(true).defaultValue("none").build()))
					.select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any())
					.paths(PathSelectors.regex("(?!/error).+")).paths(PathSelectors.regex("(?!/actuator).+")).build();
		} else {
			return null;
		}
	}
}