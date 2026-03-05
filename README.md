# FinanceAPI

## Overview

**FinanceAPI** is a RESTful backend application designed to manage personal finances. It provides endpoints to:

- Create, read, update, and delete transactions
- Track budgets
- Analyze spending using statistical calculations (mean, variance, standard deviation, totals)
- Export transactions as CSV files

This project demonstrates **clean layered architecture**, **database integration**, and **real-world backend development skills**.

---

## Features

- CRUD operations for transactions and budgets
- Analytics and statistics on user transactions
- CSV export of transactions
- DTO validation for secure data transfer
- Layered architecture (Controller → Service → Repository)
- Enum types for transaction status and type
- Payload and API response wrappers

---

## Tech Stack

**Backend**

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate

**Database**

- MySQL / PostgreSQL

**Build Tool**

- Maven

**Testing / API Interaction**

- Postman, cURL, or frontend client

---

## Project Structure


src/main/java/com/phetolo/Financeapi

FinanceapiApplication.java # Main Spring Boot application class

controller
-AnalyticsController.java # Endpoints for statistics/analytics
-BudgetController.java # Budget CRUD endpoints
-ExportController.java # CSV export endpoints
-TransactionController.java # Transaction CRUD endpoints
-UserController.java # User management endpoints

dto
BudgetDTO.java
-StatisticDTO.java # Statistics response DTO
-TransactionDTO.java
-UserDTO.java

enums
-TransactionStatus.java # e.g., PENDING, COMPLETED
-TransactionType.java # e.g., INCOME, EXPENSE

exception
-(custom exceptions for error handling)

mapper
-BudgetMapper.java
-TransactionMapper.java

model
-Budget.java
-Transaction.java
-User.java

payload
-ApiResponse.java # Generic API response wrapper

repository
-BudgetRepository.java
-TransactionRepository.java
-UserRepository.java

service
-AnalyticsService.java # Business logic for statistics
-BudgetService.java
-ExportService.java # CSV export logic
-TransactionService.java
-UserService.java


---

## Core Entities

### User
Represents an account owning transactions and budgets.

- `id`
- `username`
- `email`
- `password`

### Transaction
Represents a financial record.

- `id`
- `amount` (BigDecimal)
- `category`
- `type` (TransactionType enum)
- `status` (TransactionStatus enum)
- `description`
- `date`
- `userId`

### Budget
Tracks spending limits for a category.

- `id`
- `category`
- `monthlyLimit`
- `userId`

---

## API Endpoints

### Transactions

- `POST /transactions` – Create a new transaction  
- `GET /transactions` – Get all transactions  
- `GET /transactions/{id}` – Get a transaction by ID  
- `DELETE /transactions/{id}` – Delete a transaction  

### Analytics

- `GET /analytics/statistics?userId={id}` – Get statistics (mean, variance, std dev, sum, min, max, count)

### CSV Export

- `GET /export/transactions/csv/{userId}` – Download a CSV file of transactions

### Budgets

- `POST /users/{userId}/budgets` – Create a new budget  
- `GET /users/{userId}/budgets` – Get all budgets  
- `GET /users/{userId}/budgets/{id}` – Get a budget by ID  
- `DELETE /users/{userId}/budgets/{id}` – Delete a budget  

### Users

- `POST /users` – Register a new user  
- `GET /users/{id}` – Get user details  

---

## Running the Project

### 1. Clone the repository

```bash
git clone https://github.com/yourusername/FinanceAPI.git

2. Navigate into the project

cd financeapi

3. Configure the database

Update application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/finance_tracker
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

4. Run the application

mvn spring-boot:run

The API will start at

http://localhost:8080

---

## Testing the API

You can test the API using:

- Postman
- cURL
- Insomnia

Example request

GET http://localhost:8080/users/{userId}/transactions

---

## Future Improvements

- User authentication with Spring Security and JWT
- Pagination for transactions
- Budget alerts when spending exceeds limits
- Financial analytics and reporting
- Docker containerization
- Frontend dashboard integration

---

## Learning Goals

-This project focuses on developing backend engineering skills including:
-REST API design
-Clean layered architecture
-Database integration using JPA
-Statistics and financial analytics
-File export features
-DTO and payload usage for secure API responses
-Git and GitHub workflow

---

## Author

Phetolo Tshukudu

BSc Computer Science & Mathematical Statistics  
Aspiring backend developer interested in financial technology and scalable backend systems