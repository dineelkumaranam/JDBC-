# JDBC MVC Student Management System

A beginner-friendly Java MVC application using JDBC and MySQL.

## Architecture
Browser/HTML -> Servlet Controller -> DAO/Model -> JDBC -> MySQL

## Requirements
- JDK 17+
- Apache Tomcat 10+
- MySQL 8+
- Maven 3.9+

## Database
Run database/schema.sql in MySQL.

## Configure
Edit src/main/resources/db.properties:
db.password=YOUR_MYSQL_PASSWORD

## Run
1. mvn clean package
2. Deploy target/jdbc-mvc-student.war to Tomcat 10.
3. Open http://localhost:8080/jdbc-mvc-student/

The project uses MySQL Connector/J through Maven.
