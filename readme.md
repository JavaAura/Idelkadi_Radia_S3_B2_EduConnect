# 📚 Project: Digitalization of Training Management

## 📜 Project Context

A training center aims to digitalize its training management through a REST API. This platform will manage trainings, learners, trainers, and training sessions.

## 🔑 Key Features

- **Learner Management**:
    - Registration
    - Modification
    - Deletion
    - Display one or multiple learners

- **Trainer Management**:
    - Creation
    - Modification
    - Deletion
    - Display one or multiple trainers

- **Training Management**:
    - Planning
    - Modification
    - Deletion
    - Display one or multiple trainings

- **Class Management**:
    - Creation
    - Modification
    - Deletion
    - Display one or multiple classes

## 📦 Main Class Structure

- **Learner**:
    - `name`
    - `firstName`
    - `email`
    - `level`
    - `training`
    - `class`

- **Trainer**:
    - `name`
    - `firstName`
    - `email`
    - `specialty`
    - `training`
    - `class`

- **Class**:
    - `name`
    - `roomNumber`

- **Training**:
    - `title`
    - `level`
    - `prerequisites`
    - `minCapacity`
    - `maxCapacity`
    - `startDate`
    - `endDate`
    - `trainer`
    - `learners`
    - `status` (PLANNED, IN_PROGRESS, COMPLETED, CANCELED)

## 🛠 Application Layers

- **Entities** (with JPA annotations and validation)
- **Repositories** (extends `JpaRepository`)
- **Services**
- **Controllers** (REST)
- **Exceptions**
- **Utilities**
- **Tests**

## ⚙ Technologies Used

- **Spring Boot**
    - Project setup via Spring Initializer
    - Configuration in `application.properties`
    - Multiple profiles (dev and prod)

- **REST API**
    - CRUD operations for entities with appropriate HTTP methods
    - Testing via Postman and API documentation using Swagger

- **Spring Data JPA**
    - Utilization of `JpaRepository`
    - Custom search methods and pagination

- **Exception Handling**
- **Validation** with annotations like `@Valid` and `@NotNull`


## 🗄 Databases Used

- **H2** (dev profile): for development and testing
- **PostgreSQL** (prod profile): for production environment

## 📋 Technical Requirements

- Inversion of Control (IoC) and Dependency Injection (DI)
- Code coverage with JaCoCo
- Java 8 development
- Maven for dependency management
- Unit and integration tests with JUnit and Mockito
- Implementation of Repository and Singleton design patterns
- Logging with a logging system
- Spring Data for data access
- Validation application

### Java 8 Features Utilized

- Stream API
- Lambda expressions
- Java Time API
- Collection API and Hashmaps
- Optional

## 🛠 Tools Used

- Git for version control
- IntelliJ IDEA as the IDE
- JIRA for project management (Scrum methodology)
- Lombok for boilerplate code reduction
- Spring Boot DevTools for rapid development
- SonarLint for code quality checks


## 📅 Project Details

- **Type**: Individual Project
- **Duration**: 5 days
- **Start Date**: 28/10/2024
- **Deadline**: 01/11/2024

## ⚙ Installation and Execution

### 📋 Prerequisites

- Java 8 or higher
- Maven
- PostgreSQL for the prod profile
- H2 for the dev profile
- IDE of your choice (IntelliJ, Eclipse, etc.)

### 🛠 Clone the Project

```bash
git clone <your-repo-link>
```

### 🚀 Run the Application

To start the application with the dev profile:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

To start the application with the prod profile:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```
### 🌐 Access the API

The API will be accessible at the following addresses:

    Development: http://localhost:8080/api
    Production: http://localhost:8080/api

### 📖 API Documentation

The Swagger documentation is accessible at the following URL after starting the application:

```bash

http://localhost:8080/swagger-ui.html
```

### 📝 Author

Your Name
[Your Email Address]
[Your LinkedIn Profile or GitHub Link]