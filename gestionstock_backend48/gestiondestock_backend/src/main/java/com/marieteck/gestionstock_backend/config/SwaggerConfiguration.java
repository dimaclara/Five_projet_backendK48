package com.marieteck.gestionstock_backend.config;

import com.marieteck.gestionstock_backend.utils.Constants;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static java.awt.SystemColor.info;


@Configuration
public class SwaggerConfiguration {
 @Bean
    public  OpenAPI customOpenAPI(){
     return new OpenAPI().info(
             new Info()
                     .title("Stock API")
                     .version("1.0")
                     .description("API Documentation for inventory Management Application ")
                     .license(new License()
                             .name("Apache 2.0")
                             .url("https://springdoc.org"))

                     .contact(new Contact()
                             .name("Marie Clara")
                             .email("marieclaraassehe15@gmail.com")
                             .url("https://github.com/dimaclara/Five_projet_backendK48/tree/main" +
                                     "/gestionstock_backend48")))
             .servers(List.of(
                     new Server()
                             .description("Api entry point" )
                             .url(Constants.APP_ROOT)


                     ));





    }
}
