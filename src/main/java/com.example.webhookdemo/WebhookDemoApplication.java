package com.example.webhookdemo;

import com.example.webhookdemo.service.WebhookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebhookDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebhookDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner run(WebhookService webhookService) {
        return args -> {
            webhookService.handleWebhookProcess();
        };
    }
}
