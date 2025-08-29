package com.bajajfinserv.health.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sample REST controller for Bajaj Finserv Health
 */
@RestController
@RequestMapping("/api/health")
public class HealthController {

    @GetMapping("/status")
    public String getHealthStatus() {
        return "Bajaj Finserv Health Service is running successfully!";
    }

    @GetMapping("/info")
    public HealthInfo getHealthInfo() {
        return new HealthInfo("Bajaj Finserv Health", "1.0.0", "UP");
    }

    /**
     * Inner class to represent health information
     */
    public static class HealthInfo {
        private String serviceName;
        private String version;
        private String status;

        public HealthInfo(String serviceName, String version, String status) {
            this.serviceName = serviceName;
            this.version = version;
            this.status = status;
        }

        // Getters and Setters
        public String getServiceName() { return serviceName; }
        public void setServiceName(String serviceName) { this.serviceName = serviceName; }
        
        public String getVersion() { return version; }
        public void setVersion(String version) { this.version = version; }
        
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}
