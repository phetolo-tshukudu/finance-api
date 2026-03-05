# Finance Tracker API

## Overview
The Finance Tracker API is a RESTful backend application that allows users to manage and track their financial transactions such as income and expenses.

The API provides endpoints for creating, retrieving, and managing financial records while following a clean layered architecture.

This project was built to practice real-world backend development concepts such as REST API design, database integration, DTO validation, and structured application architecture.

---

## Features

- Create financial transactions
- View all recorded transactions
- Categorize income and expenses
- Track budgets by category
- Persistent storage using a relational database
- Input validation using DTOs
- Layered architecture (Controller → Service → Repository)
- RESTful API endpoints

---

## Tech Stack

Backend
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate

Database
- MySQL / PostgreSQL

Build Tool
- Maven

Testing
- Postman

---

## Project Structure

src/main/java/com/yourpackage/financetracker

controller
- TransactionController

service
- TransactionService

repository
- TransactionRepository

model
- Transaction

dto
- TransactionDTO

FinanceTrackerApplication

The application follows a layered architecture:

Controller → Service → Repository → Database

---

## API Endpoints

Create Transaction

POST /transactions

Example request body

{
  "amount": 250,
  "category": "Groceries",
  "description": "Weekly shopping",
  "date": "2026-02-25"
}

---

Get All Transactions

GET /transactions

Returns a list of stored transactions.

---

Get Transaction by ID

GET /transactions/{id}

---

Delete Transaction

DELETE /transactions/{id}

---

## Running the Project

1. Clone the repository

git clone https://github.com/yourusername/finance-tracker-api.git

2. Navigate into the project

cd finance-tracker-api

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

GET http://localhost:8080/transactions

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

This project focuses on developing backend engineering skills including:

- REST API design
- Spring Boot application development
- Database integration with JPA
- Clean architecture principles
- DTO validation
- Git and GitHub workflow

---

## Author

Phetolo Tshukudu

BSc Computer Science & Mathematical Statistics  
Aspiring backend developer interested in financial technology and scalable backend systems