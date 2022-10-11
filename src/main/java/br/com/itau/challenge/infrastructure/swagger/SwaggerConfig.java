package br.com.itau.challenge.infrastructure.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docketApi() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfo("PROJETO DESAFIO BACKEND ITAU",
                "José Robert - Developer",
                "1.0",
                "Terms of service",
                new Contact("DEVELOPER IT", "https://github.com/Jose-Robert", "jrobert.dev@hotmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }
}
