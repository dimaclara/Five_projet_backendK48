package com.marieteck.gestionstock_backend.config;

import com.marieteck.gestionstock_backend.utils.Constants;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static java.awt.SystemColor.info;


@Configuration
public class SwaggerConfiguration {


    public static final String SCHEME_NAME = "JWT";
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Stock API")
                        .version("1.0")
                        .description("API Documentation for inventory Management Application")
                        .contact(new Contact()
                                .name("Marie Clara")
                                .email("marieclaraassehe15@gmail.com")
                                .url("https://github.com/dimaclara/Five_projet_backendK48"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org"))
                )
                .servers(List.of(
                        new Server().url("/").description("Local API")
                ))
                .addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME))
                .components(new Components().addSecuritySchemes(SCHEME_NAME,
                        new SecurityScheme()
                                .name(SCHEME_NAME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                ));
    }
}

