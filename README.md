# Spring Boot 3 Security (Jwt)
This is a spring project based on `Spring Boot 3`, `Spring Security 6` and `JWT Token` that implement the security for a REST API web service.
Every user has to be authenticated and authorized with JWT token that able to work with APIs.

## How it works
There are three API address to test security methods
1. Register a user with essential information ( "/api/v1/users/register" )
2. Login to service with username and password is entered in the registration API. 
User get a token to use that in other API Headers with Authorization field. ( "/api/v1/users/login" )
3. There is an API address for the admin user to test different authorization roles ( "/api/v1/users" )
+ By default, an admin user is declared in the project with ADMIN role. Both username and password is "admin".
> ***NOTE***
>
> There is OpenAPI v3 for using these steps and others RESTful APIs. Document REST endpoints -> Swagger at http://localhost:8080/swagger-ui

## Build & Run
Project is dockerized and can use this commands to build and run the app.

To build project
```sh
mvn clean package -DskipTests
```

To run project
```sh
docker compose up -d
```

# Requirements
- Java 17
- Spring Boot 3
- Spring Security 6
- MySQL
- Maven
- Docker and Docker Compose