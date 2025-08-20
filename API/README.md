# CargoAPI-Spring-Boot

# cargapi

A simple Spring Boot API for managing cargo-related data. This application connects to a PostgreSQL database and exposes RESTful endpoints.

---

## 🚀 Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/cargapi.git && cd cargapi
```

### 2. Configure Application Properties
Update the src/main/resources/application.properties file with your database credentials:

spring.application.name=cargapi

# Replace with your actual credentials from the database dashboard
spring.datasource.url=YOUR_DATABASE_URL
spring.datasource.username=YOUR_DATABASE_USERNAME
spring.datasource.password=YOUR_DATABASE_PASSWORD

# JPA & Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

⚠️ You will receive the correct database URL, username, and password from the SUPABASE database dashboard.

### 3. Build and Run the Application
Ensure you have Java 17+ and Maven installed.

Run using Spring Boot wrapper:

```bash
mvnw spring-boot:run
```

