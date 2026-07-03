# Backend Project - Middleware REST API

## General Info
Middleware REST API for fetching and displaying products from multiple data sources.

## Table of Contents
- General Info
- Features
- Tech Stack
- Getting Started
- Authentication
- Endpoints
- Testing
- Architecture
- AI Tools Used

## Features
- Fetch and display products from DummyJSON REST API
- Product list with image, title, price and shortened description (100 chars)
- Product detail view
- Filter products by category and price range
- Search products by title
- JWT Authentication and Authorization
- Caching for repeated search/filter requests
- Extensible architecture for future data sources

## Tech Stack
- Java 26
- Spring Boot 4.1.0
- Spring Data JPA / Hibernate
- H2 Database (in-memory)
- Spring Security + OAuth2 Resource Server (JWT)
- Lombok
- SpringDoc OpenAPI / Swagger UI 2.3.0
- Spring Cache

## Getting Started

### Prerequisites
- Java 26
- Maven

### Run locally
git clone <url><br>
cd backend-project<br>
mvn spring-boot:run

Application runs on: http://localhost:8080

## Authentication
Application uses JWT authentication.
Credentials are DummyJSON users: https://dummyjson.com/users

### Get token
POST http://localhost:8080/auth/login<br>
{<br>
  "username": "emilys",<br>
  "password": "emilyspass"<br>
}

### Use token
Add header to every request:<br>
Authorization: Bearer <token>

## Endpoints
Full documentation available on Swagger UI:<br>
http://localhost:8080/swagger-ui/index.html


## Testing
mvn test<br>

Unit tests: ProductControllerTest, ProductServiceTest<br>
Integration tests: ProductRepositoryIT

## Architecture
Project uses Package by Feature structure.

## AI Tools Used
This project was developed with the assistance of Claude (Anthropic).

### Where and Why
- **Project architecture** - Used Claude to decide on project structure 
  (Package by Feature) and select appropriate Spring Boot dependencies
- **JWT Authentication** - Used Claude to help implement Spring Security OAuth2
- **JPA/Specifications** - Used Claude to implement JpaSpecificationExecutor 
  for dynamic product filtering
- **Unit & Integration Tests** - Used Claude to understand testing concepts 
  (MockitoExtension, @DataJpaTest)
- **Debugging** - Used Claude to resolve errors during development
