# Family Tree Microservice

A Spring Boot microservice for managing family tree data.

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Getting Started

1. Clone the repository
2. Navigate to the project directory
3. Build the project:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## Features

- RESTful API for managing family members
- H2 in-memory database for development
- JPA for data persistence
- Spring Boot 3.2.3

## API Endpoints

The API documentation will be available at:
- Swagger UI: http://localhost:8080/swagger-ui.html
- H2 Console: http://localhost:8080/h2-console

## Development

The project uses:
- Spring Boot for the application framework
- Spring Data JPA for data access
- H2 Database for development
- Lombok for reducing boilerplate code
- Spring Web for REST endpoints
- Spring Validation for input validation

## License

This project is licensed under the MIT License. 