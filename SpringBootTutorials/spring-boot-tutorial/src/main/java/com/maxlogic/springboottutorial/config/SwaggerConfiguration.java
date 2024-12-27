package com.maxlogic.springboottutorial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfiguration {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
        .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
        .info(apiInfo());
  }

  private SecurityScheme createAPIKeyScheme() {
    return new SecurityScheme().type(SecurityScheme.Type.HTTP)
        .bearerFormat("JWT")
        .scheme("bearer");
  }

  private Info apiInfo() {
    return new Info()
        .title("Spring Boot Tutorial Api Doc")
        .version("1.0.0")
        .description("Spring Boot Tutorial Api")
        .contact(new Contact().name("Sourabh Teke"));
  }
}
