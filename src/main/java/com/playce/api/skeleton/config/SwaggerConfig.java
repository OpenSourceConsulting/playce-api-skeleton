/*
 * Copyright 2020 The Playce Project.
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Revision History
 * Author			Date				Description
 * ---------------	----------------	------------
 * SangCheon Park	Jul 22, 2020	    First Draft.
 */
package com.playce.api.skeleton.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
@Configuration
@EnableSwagger2
@Profile({"local", "dev"})
public class SwaggerConfig {

	@Autowired
	private TypeResolver typeResolver;

	@Value("${spring.profiles.active:local}")
	private String activeProfile;

	@Bean
	public Docket api() {
		init();

        List<Parameter> parameters = getGlobalParameters();

        /*
        parameters.add(new ParameterBuilder().name("lang")
            	.modelRef(new ModelRef("string"))
            	.parameterType("query")
            	.required(false)
            	.description("language")
            	.build());
		//*/

		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("api")
				.apiInfo(apiInfo())
				.select() 
				//.apis(RequestHandlerSelectors.any())
				//.paths(PathSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.playce.api.skeleton.controller"))
				.paths(PathSelectors.ant("/api/**"))
				.build().globalOperationParameters(parameters);
	}
	
	@Bean
	public Docket auth() {
        List<Parameter> parameters = new ArrayList<Parameter>();

        /*
        parameters.add(new ParameterBuilder().name("lang")
            	.modelRef(new ModelRef("string"))
            	.parameterType("query")
            	.required(false)
            	.description("language")
            	.build());
        //*/

		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("auth")
				.apiInfo(apiInfo())
				.select() 
				.apis(RequestHandlerSelectors.basePackage("com.playce.api.skeleton.controller"))
				.paths(PathSelectors.ant("/auth/**"))
				.build().globalOperationParameters(parameters);
	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	            .title("Playce")
	            .description("API Documentation")
	            .license("© " + Calendar.getInstance().get(Calendar.YEAR) + " Open Source Consulting Inc.")
	            .licenseUrl("http://www.osci.kr")
	            .version("1.0")
	            .build();
	}

	/**
	 * Init. Not Effective
	 */
	private void init() {
		// do something to initialize
	}

	/**
	 * Gets global parameters.
	 *
	 * @return the global parameters
	 */
	private List<Parameter> getGlobalParameters() {
		String token = "{TOKEN}";

		if ("local".equals(activeProfile)) {
			token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOlt7ImF1dGhvcml0eSI6IkFkbWluaXN0cmF0b3IifV0sImNyZWF0ZWQiOjE1OTU0NjQ2NzA2NTEsImV4cCI6MTU5NTk4MzA3MH0.r0c-7UQNegpnRasRPcfwEGIs-4vRKSLQgGlDMYaMSjzg2jyXkA921lMOZOqVzis69Poj2ZeXPgxLxi9rxl2wfg";
		}

		List<Parameter> parameters = new ArrayList<Parameter>();
		parameters.add(new ParameterBuilder().name("Authorization")
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.required(true)
				.defaultValue("Bearer " + token)
				.description("JWT Authentication")
				.build());

		return parameters;
	}
}
// end of SwaggerConfig.java