package com.bajajfinserv.health.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;

@Service
@Slf4j
public class HealthService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final RestTemplate restTemplate = new RestTemplate();

    public String getSystemHealth() {
        String currentTime = LocalDateTime.now().format(FORMATTER);
        return String.format("System is healthy at %s", currentTime);
    }

    public void registerWebhook() {
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

        Map<String, String> body = new HashMap<>();
        body.put("name", "Naga Rithesh");
        body.put("regNo", "22BRS1006");
        body.put("email", "naga.rithesh2022@vitstudent.ac.in");
        
        ResponseEntity<Map> response = restTemplate.postForEntity(url, body, Map.class);

        String webhookUrl = (String) response.getBody().get("webhook");
        String accessToken = (String) response.getBody().get("accessToken");
        
        if (webhookUrl == null || accessToken == null) {
            log.error("Missing webhook URL or access token in response");
            return;
        }
        
        String sqlQuery = solveSQL();
        submitSQL(webhookUrl, accessToken, sqlQuery);
    }

    private String solveSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("    e.EMP_ID, ");
        sql.append("    e.FIRST_NAME, ");
        sql.append("    e.LAST_NAME, ");
        sql.append("    d.DEPARTMENT_NAME, ");
        sql.append("    COUNT(*) OVER ( ");
        sql.append("        PARTITION BY e.DEPARTMENT_T ");
        sql.append("        ORDER BY e.DOB DESC ");
        sql.append("        ROWS BETWEEN UNBOUNDED PRECEDING AND 1 PRECEDING ");
        sql.append("    ) AS YOUNGER_EMPLOYEES_COUNT ");
        sql.append("FROM ");
        sql.append("    EMPLOYEE e ");
        sql.append("JOIN ");
        sql.append("    DEPARTMENT d ON e.DEPARTMENT_T = d.DEPARTMENT_ID ");
        sql.append("ORDER BY ");
        sql.append("    e.EMP_ID DESC;");

        return sql.toString();
    }

    private void submitSQL(String webhookUrl, String token, String sqlQuery) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> body = new HashMap<>();
            body.put("finalQuery", sqlQuery);

            HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(webhookUrl, entity, String.class);
            log.info("SQL submission successful: {}", response.getBody());
        } catch (Exception e) {
            log.error("Error submitting SQL: {}", e.getMessage(), e);
        }
    }

    public String getServiceUptime() {
        log.info("Retrieving service uptime information");
        return "Service has been running since startup";
    }
}
