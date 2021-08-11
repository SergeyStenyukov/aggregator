package com.sirenatravel.aggregator.config;

import com.sirenatravel.aggregator.AggregatorApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket aggregatorApi(ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(AggregatorApplication.class.getPackageName()))
                .paths(regex("/v1.0.*")).build().apiInfo(apiInfo);
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Aggregator API").version("1.0.0")
                .contact(new Contact("Sergey", "aggregator.com", "stenyukovs@gmail.com"))
                .build();
    }
}
