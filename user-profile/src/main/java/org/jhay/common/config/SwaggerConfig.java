package org.jhay.common.config;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
@Configuration
public class SwaggerConfig {
    @Value("v1")
    private String version;
    @Bean
    public OpenAPI api() {
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("jwt", securityScheme))
                .info(new Info()
                        .title("LOAN APP USER_PROFILE-SERVICE")
                        .description("The user profile microservice for the loan application")
                        .version(version))
                .security(Collections.singletonList(new SecurityRequirement().addList("jwt")));
    }
}
