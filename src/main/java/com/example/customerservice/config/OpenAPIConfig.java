package com.example.customerservice.config;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Customer Service API",
        version = "1.0",
        description = "API for managing customer information"
    )
)
public class OpenAPIConfig {
}
