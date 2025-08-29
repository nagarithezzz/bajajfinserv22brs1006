# Bajaj Finserv Health

A Spring Boot application for Bajaj Finserv Health services.

## Features

- Spring Boot 3.2.0
- Spring Web (RestTemplate/WebClient support)
- Lombok for reducing boilerplate code
- RESTful API endpoints
- Health monitoring capabilities

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── bajajfinserv/
│   │           └── health/
│   │               ├── BajajFinservHealthApplication.java
│   │               ├── controller/
│   │               │   └── HealthController.java
│   │               └── service/
│   │                   └── HealthService.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/
            └── bajajfinserv/
                └── health/
```

## Getting Started

### 1. Build the Project

```bash
mvn clean install
```

### 2. Run the Application

```bash
mvn spring-boot:run
```

Or run the JAR file:

```bash
java -jar target/bajaj-finserv-health-1.0.0.jar
```

### 3. Access the Application

The application will start on port 8080. You can access:

- Health Status: http://localhost:8080/api/health/status
- Health Info: http://localhost:8080/api/health/info

## API Endpoints

### GET /api/health/status
Returns a simple health status message.

### GET /api/health/info
Returns detailed health information including service name, version, and status.

## Dependencies

- **Spring Boot Starter**: Core Spring Boot functionality
- **Spring Web**: Web development support including RestTemplate and WebClient
- **Lombok**: Reduces boilerplate code with annotations
- **Spring Boot Test**: Testing support

## Configuration

The application can be configured through `src/main/resources/application.properties`:

- Server port: 8080
- Application name: Bajaj Finserv Health
- Logging level: INFO

## Development

This project demonstrates:
- Spring Boot application setup
- REST controller implementation
- Service layer with Lombok annotations
- Basic health monitoring endpoints

## License

This project is for demonstration purposes.
