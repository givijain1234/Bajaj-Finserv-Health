package com.example.webhookdemo.service;

import com.example.webhookdemo.model.WebhookRequest;
import com.example.webhookdemo.model.WebhookResponse;
import com.example.webhookdemo.model.FinalQueryPayload;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

    private final RestTemplate restTemplate = new RestTemplate();

    public void handleWebhookProcess() {
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
        WebhookRequest requestBody = new WebhookRequest("John Doe", "REG12347", "john@example.com");
        ResponseEntity<WebhookResponse> response = restTemplate.postForEntity(url, requestBody, WebhookResponse.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            String   = response.getBody().getWebhook();
            String token = response.getBody().getAccessToken();
            String finalQuery = """
                SELECT
                    p.AMOUNT AS SALARY,
                    CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS NAME,
                    e.DOB,
                    d.DEPARTMENT_NAME
                FROM PAYMENTS p
                JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID
                JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID
                WHERE DAY(p.PAYMENT_TIME) != 1
                  AND p.AMOUNT = (
                      SELECT MAX(AMOUNT)
                      FROM PAYMENTS
                      WHERE DAY(PAYMENT_TIME) != 1
                  )
                LIMIT 1;
            """;
          
            FinalQueryPayload payload = new FinalQueryPayload(finalQuery);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(token);

            HttpEntity<FinalQueryPayload> request = new HttpEntity<>(payload, headers);

            ResponseEntity<String> postResponse = restTemplate.postForEntity(webhook, request, String.class);
            System.out.println("Final Query submitted. Response: " + postResponse.getStatusCode());
        } else {
            System.err.println("Failed to generate webhook or get token.");
        }
    }
}
