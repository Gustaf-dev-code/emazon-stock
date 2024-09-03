package com.example.emazon;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Emazon API", version = "1.0", description = "API for Emazon Application"))

public class EmazonApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmazonApplication.class, args);
    }

}
