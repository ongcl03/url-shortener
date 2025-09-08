# 🔗 URL Shortener

A modern, scalable URL shortening service built with **Spring Boot** and **Java 21**. Transform long URLs into short, manageable links with enterprise-grade features and performance.

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-green.svg)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-red.svg)](https://maven.apache.org/)

## 🚀 Features

- **Fast URL Shortening**: Generate short, unique 6-character alphanumeric codes
- **Instant Redirection**: Seamless redirect from short URLs to original destinations
- **RESTful API**: Clean, documented API endpoints with OpenAPI/Swagger integration
- **Database Persistence**: Reliable storage with MySQL and JPA/Hibernate
- **Schema Migration**: Version-controlled database migrations with Flyway
- **Input Validation**: Comprehensive request validation with custom error handling
- **Auto-Documentation**: Interactive API documentation with Swagger UI
- **Enterprise Architecture**: Layered architecture with proper separation of concerns

## 🏗️ Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Controllers   │───▶│    Services     │───▶│  Repositories   │
│                 │    │                 │    │                 │
│ • URL Creation  │    │ • Business      │    │ • Data Access   │
│ • Redirection   │    │   Logic         │    │ • JPA Queries   │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         ▼                       ▼                       ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│      DTOs       │    │    Entities     │    │     MySQL       │
│                 │    │                 │    │                 │
│ • Request/      │    │ • UrlMapping    │    │ • url_mapping   │
│   Response      │    │ • Domain Model  │    │   table         │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

## 🛠️ Technology Stack

| Component         | Technology        | Purpose                                    |
| ----------------- | ----------------- | ------------------------------------------ |
| **Backend**       | Spring Boot 3.5.5 | Web framework and dependency injection     |
| **Language**      | Java 21           | Modern Java with latest features           |
| **Database**      | MySQL 8.0+        | Relational database for data persistence   |
| **ORM**           | Spring Data JPA   | Object-relational mapping                  |
| **Migration**     | Flyway            | Database version control                   |
| **Documentation** | SpringDoc OpenAPI | API documentation and testing              |
| **Validation**    | Bean Validation   | Input validation and error handling        |
| **Build Tool**    | Maven             | Dependency management and build automation |
| **Code Quality**  | Lombok            | Boilerplate code reduction                 |

## 📚 API Endpoints

### Create Short URL

```http
POST /create-url
Content-Type: application/json

{
  "longUrl": "https://www.example.com/very/long/url/path"
}
```

**Response:**

```json
{
  "shortUrl": "http://localhost:8080/abc123"
}
```

### Redirect to Original URL

```http
GET /{shortCode}
```

- **shortCode**: 6-character alphanumeric identifier
- **Response**: HTTP 302 redirect to original URL

## 🔧 Quick Start

### Prerequisites

- Java 21 or higher
- MySQL 8.0+
- Maven 3.6+

### 1. Clone the Repository

```bash
git clone https://github.com/ongcl03/url-shortener.git
cd url-shortener
```

### 2. Database Setup

```sql
CREATE DATABASE url_shortener;
CREATE USER 'url_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON url_shortener.* TO 'url_user'@'localhost';
```

### 3. Configure Application

Update `src/main/resources/application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/url_shortener
    username: url_user
    password: password
```

### 4. Run Database Migrations

```bash
mvn flyway:migrate
```

### 5. Start the Application

```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`

## 📖 API Documentation

Once the application is running, access the interactive API documentation:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI Spec**: http://localhost:8080/v3/api-docs

## 🗄️ Database Schema

```sql
CREATE TABLE Url_Mapping (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    short_code VARCHAR(10) NOT NULL UNIQUE,
    long_url VARCHAR(2048) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP NULL
);
```

## 🚀 Future Enhancements

- [ ] Custom short URLs
- [ ] Expiration date management
- [ ] Click analytics dashboard
- [ ] User authentication and URL management
- [ ] REST API versioning

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request
