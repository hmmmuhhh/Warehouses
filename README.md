## 🔧 Configuration

This project requires an `application.yml` file for database and environment configuration.

Create a file at `src/main/resources/application.yml` with the following structure:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/your_db_name
    username: your_username
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

flyway:
  enabled: true

# replace the environment variable names with your local names (eg. Warehouses, postgres, 12345)
