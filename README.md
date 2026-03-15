# FinanceAPI

## Overview

**FinanceAPI** is a RESTful backend application designed to manage personal finances. It provides endpoints to:

* Create, read, update, and delete transactions
* Track budgets and spending limits
* Analyze spending using statistical calculations (mean, variance, standard deviation, totals)
* Export transactions as CSV files
* Secure access using JWT authentication and role-based authorization

This project demonstrates **clean layered architecture**, **database integration**, **secure authentication**, and **real-world backend development skills**.

---

# Features

### Financial Management

* CRUD operations for transactions and budgets
* Analytics and statistics on user transactions
* CSV export of transactions

### Security

* JWT-based authentication
* Password encryption using BCrypt
* Role-based authorization (**ADMIN / USER**)
* Protected endpoints using Spring Security
* User identity derived from JWT token (no `userId` required in requests)

### Architecture

* Layered architecture (Controller → Service → Repository)
* DTO validation for secure request handling
* Enum types for transaction status and type
* Payload and API response wrappers

---

# Tech Stack

## Backend

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* Hibernate
* JWT Authentication

## Database

* MySQL

## Build Tool

* Maven

## Testing / API Interaction

* Postman

---

# Project Structure

src/main/java/com/phetolo/Financeapi

FinanceapiApplication.java # Main Spring Boot application

controller

* AnalyticsController.java # Statistics endpoints
* AuthController.java # Authentication endpoints
* BudgetController.java # Budget CRUD
* ExportController.java # CSV export
* TransactionController.java # Transaction CRUD
* AdminController.java # Admin user management

dto

* BudgetDTO.java
* StatisticDTO.java
* TransactionDTO.java
* UserDTO.java
* LoginRequest.java
* LoginResponse.java

enums

* TransactionStatus.java
* TransactionType.java
* Role.java

exception

* (custom exception handling)

mapper

* BudgetMapper.java
* TransactionMapper.java

model

* Budget.java
* Transaction.java
* User.java

payload

* ApiResponse.java

repository

* BudgetRepository.java
* TransactionRepository.java
* UserRepository.java

security

* JwtAuthenticationFilter.java
* JwtService.java
* SecurityConfig.java
* CustomUserDetailsService.java

service

* AnalyticsService.java
* BudgetService.java
* ExportService.java
* TransactionService.java
* UserService.java

---

# Core Entities

## User

Represents an authenticated account.

Fields:

* `id`
* `name`
* `email`
* `password`
* `role`
* `active`
* `createdAt`

Roles:

* **USER** – standard access to personal finance data
* **ADMIN** – management access to users

---

## Transaction

Represents a financial record.

Fields:

* `id`
* `amount` (BigDecimal)
* `category`
* `type` (TransactionType enum)
* `status` (TransactionStatus enum)
* `description`
* `date`
* `user`

---

## Budget

Tracks monthly spending limits.

Fields:

* `id`
* `category`
* `monthlyLimit`
* `user`

---

# Authentication

The API uses **JWT authentication**.

### Login

POST /auth/login

Request:

```
{
  "username": "user@example.com",
  "password": "password"
}
```

Response:

```
{
  "token": "JWT_TOKEN"
}
```

Use the token in requests:

```
Authorization: Bearer JWT_TOKEN
```

---

# API Endpoints

## Authentication

POST /auth/login
POST /auth/register

---

## Transactions

POST /transactions – Create transaction
GET /transactions – Get all user transactions
GET /transactions/{id} – Get transaction by ID
DELETE /transactions/{id} – Delete transaction

*(User is automatically derived from the JWT token.)*

---

## Budgets

POST /budgets – Create budget
GET /budgets – Get all budgets
GET /budgets/{id} – Get budget by ID
DELETE /budgets/{id} – Delete budget

---

## Analytics

GET /analytics/statistics

Returns:

* mean
* variance
* standard deviation
* total spending
* minimum transaction
* maximum transaction
* transaction count

---

## CSV Export

GET /export/transactions/csv

Downloads a CSV file containing the user's transactions.

---

## Admin Endpoints

Restricted to **ADMIN role**.

GET /admin/users
GET /admin/users/id/{id}
GET /admin/users/email/{email}

---

# Running the Project

## 1. Clone the repository

```
git clone https://github.com/yourusername/FinanceAPI.git
```

## 2. Navigate into the project

```
cd FinanceAPI
```

## 3. Configure the database

Update `application.properties`

```
spring.datasource.url=jdbc:mysql://localhost:3306/finance_tracker
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 4. Run the application

```
mvn spring-boot:run
```

The API will start at:

```
http://localhost:8080
```

---

# Testing the API

You can test the API using:

* Postman
* cURL
* Insomnia

Example request:

```
GET http://localhost:8080/transactions
```

with header:

```
Authorization: Bearer JWT_TOKEN
```

---

# Future Improvements

* Pagination for transactions
* Budget alerts when spending exceeds limits
* Advanced financial analytics
* Docker containerization
* Automated testing
* Frontend dashboard integration

---

# Learning Goals

This project focuses on backend engineering concepts including:

* REST API design
* Secure authentication using JWT
* Role-based access control
* Clean layered architecture
* Database integration using JPA
* Financial analytics and statistics
* File export features
* DTO validation and API response structures
* Git and GitHub workflow

---

# Author

**Phetolo Tshukudu**

BSc Computer Science & Mathematical Statistics
Aspiring backend developer interested in **financial technology and scalable backend systems**
