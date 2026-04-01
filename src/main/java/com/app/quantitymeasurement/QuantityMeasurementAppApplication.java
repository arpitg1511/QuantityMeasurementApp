package com.app.quantitymeasurement;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuantityMeasurementAppApplication {

    public static void main(String[] args) {
        // Explicitly load .env variables into system properties for Spring Boot
        Dotenv dotenv = Dotenv.configure()
                .directory("src/main/resources")
                .load();

        dotenv.entries().forEach(entry -> 
            System.setProperty(entry.getKey(), entry.getValue())
        );

        SpringApplication.run(QuantityMeasurementAppApplication.class, args);
    }
}
