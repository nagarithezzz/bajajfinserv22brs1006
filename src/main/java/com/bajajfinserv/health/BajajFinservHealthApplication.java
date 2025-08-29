package com.bajajfinserv.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import com.bajajfinserv.health.service.HealthService;

/**
 * Main Spring Boot application class for Bajaj Finserv Health
 */
@SpringBootApplication
public class BajajFinservHealthApplication {

    public static void main(String[] args) {
        SpringApplication.run(BajajFinservHealthApplication.class, args);
    }
    @Bean
    CommandLineRunner run(HealthService healthService) {
        return args -> healthService.registerWebhook();
    }
}
