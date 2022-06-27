package com.company.secondhand;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.company.secondhand.user")
@OpenAPIDefinition(
        info = @Info(
                title = "Secondhand-User API",
                description = "E-commerce",
                version = "v1"
        )
)
public class SecondhandApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondhandApplication.class, args);
    }

}
