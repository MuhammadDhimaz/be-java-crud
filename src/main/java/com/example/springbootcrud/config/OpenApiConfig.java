package com.example.springbootcrud.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${server.port:8080}")
    private String serverPort;

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:" + serverPort);
        server.setDescription("Development Server");

        Contact contact = new Contact();
        contact.setEmail("developer@example.com");
        contact.setName("API Support");
        contact.setUrl("https://www.example.com");

        License license = new License()
                .name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Spring Boot CRUD API")
                .version("1.0.0")
                .contact(contact)
                .description("This API provides CRUD operations for User management using Spring Boot and PostgreSQL.")
                .termsOfService("https://www.example.com/terms")
                .license(license);

        return new OpenAPI()
                .info(info)
                .servers(List.of(server));
    }
}
