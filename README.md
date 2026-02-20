📚 EC-OnlineBookShop

A full-featured Online Book Shop Backend Application built using Java 21, Spring Boot 3, Spring Data JPA, Hibernate, and Maven.

This project demonstrates real-world backend development including REST APIs, layered architecture, database relationships, and authentication.

📖 Introduction

EC-OnlineBookShop is designed to manage an online bookstore system where:

Users can browse books

Users can register and login

Users can add books to cart

Users can place orders

Admin can manage books and categories

The application follows:

MVC Architecture

RESTful API Design

Layered Architecture (Controller → Service → Repository)

JPA & Hibernate ORM

🛠️ Technologies Used

Java 21

Spring Boot 3

Spring Data JPA

Hibernate

Maven

MySQL

Spring Security (if implemented)

Swagger (for API documentation)

📂 Project Structure
controller/   → REST Controllers handling HTTP requests  
service/      → Business logic layer  
repository/   → JPA repository interfaces  
entity/       → JPA entity classes  
config/       → Security configuration  
resources/    → application.properties  
main/         → Application entry point  
🚀 Features
📚 Book Management

Add new book

Update book details

Delete book

View all books

Search books by title/author

👤 User Management

User registration

Login authentication

Role-based access (Admin/User)

🛒 Cart Management

Add books to cart

Update quantity

Remove books from cart

View cart details

📦 Order Management

Place order

View order history

Track order status

🗄️ Database Schema
Book

id (Primary Key)

title

author

price

description

stock

category_id (FK → Category)

User

id (Primary Key)

name

email

password

role

Cart

id (Primary Key)

user_id (FK → User)

book_id (FK → Book)

quantity

Order

id (Primary Key)

user_id (FK → User)

order_date

total_amount

status

Category

id (Primary Key)

name

description

⚙️ Setup Instructions
1️⃣ Clone the Repository
git clone <your-repo-url>
cd EC-OnlineBookShop
2️⃣ Configure Database

Open:

src/main/resources/application.properties

Update with your MySQL credentials:

spring.datasource.url=jdbc:mysql://localhost:3306/bookshopdb
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
3️⃣ Run the Application

Using Maven:

mvn spring-boot:run

OR generate JAR file:

mvn clean package
java -jar target/EC-OnlineBookShop-0.0.1-SNAPSHOT.jar
🌐 Access the Application

Base URL:

http://localhost:8080

If Swagger is enabled:

http://localhost:8080/swagger-ui/index.html

Use Postman or Swagger UI to test the APIs.

📡 Sample API Endpoints
Book APIs

GET /books

GET /books/{id}

POST /books

PUT /books/{id}

DELETE /books/{id}

User APIs

POST /users/register

POST /users/login

Cart APIs

POST /cart/add

GET /cart/{userId}

Order APIs

POST /orders/place

GET /orders/{id}

🎯 Skills Demonstrated

Backend Development using Spring Boot

REST API Development

JPA Entity Relationships

Authentication & Authorization

Maven Build Lifecycle

Clean Project Architecture

🤝 Contributing

Fork the repository and submit a pull request.

📜 License

This project is developed for educational and internship purposes only.

If you want Siva, I can now prepare:

🔥 Interview explanation for this project

📄 Resume-ready short description

🎤 Viva presentation script

🧠 Possible interview questions & answers

Tell me what you want next 🚀

once read the zip file and give the readme file

Good Siva 👍 I checked your ZIP project structure.

Based on your actual code (Controllers, Services, Mongo, Excel upload, Exception handling, Profiles, etc.), here is a proper README.md specifically written for YOUR project 👇

You can directly copy this into your GitHub README.md.

📚 EC-OnlineBookShop

A comprehensive Online Book Shop Backend Application built using Java 21, Spring Boot 3, Spring Data JPA, MongoDB, and Maven.

This application supports book management, customer handling, cart system, order processing, reviews, file uploads, Excel imports, and multi-environment configuration.

📖 Project Overview

EC-OnlineBookShop is a full-featured backend system designed for managing an online bookstore.

It includes:

📚 Book management

👤 Customer & user registration

🛒 Cart management

📦 Order processing

⭐ Review system

📂 File upload & Excel import

🗃️ MySQL + MongoDB integration

🌍 Multi-environment profiles (dev, qa, prod, perf, sec)

🧪 JUnit test cases

The project follows:

MVC Architecture

Layered Architecture (Controller → Service → Repository)

Exception Handling using @ControllerAdvice

DTO Pattern

Environment-based Configuration

🛠️ Technologies Used

Java 21

Spring Boot 3

Spring Data JPA

Hibernate

MongoDB

MySQL

Maven

JUnit (Testing)

REST APIs

📂 Project Structure
com.nit
│
├── controller        → REST Controllers
├── service           → Service Interfaces
├── serviceImpl       → Business Logic Implementation
├── repo              → JPA Repositories
├── mongo             → MongoDB Repositories
├── entity            → JPA Entities
├── model             → DTO Classes
├── exception         → Custom Exception Handling
├── utility           → Constants & Helper Classes
└── main class        → EcOnlineBookShopApplication.java
🚀 Features
📚 Book Module

Add new book

Update book details

Delete book

Get all books

Upload books using Excel

File upload support

👤 Customer & User Module

Customer registration

User registration

Login functionality

MongoDB-based user storage (UserRegistrationMongo)

🛒 Cart Module

Add books to cart

View cart details

Remove items from cart

📦 Order Module

Place new order

View order details

Order DTO handling (OrdersModuleDto)

⭐ Review Module

Add review for books

View reviews

Review DTO implementation

📂 File Handling

Upload files

Store file metadata

Excel-based bulk upload

❗ Exception Handling

Custom Exceptions:

BookNotFoundException

CustomerIdNotFound

GlobalExceptionHandler using @ControllerAdvice

Standard error response model

🌍 Multi-Environment Profiles

Application supports:

application-dev.properties

application-qa.properties

application-prod.properties

application-perf.properties

application-sec.properties

🗄️ Database Design
MySQL Entities

Books

Customer

Cart

Orders

Review

FileEntity

MongoDB Collection

UserRegistrationMongo

⚙️ Setup Instructions
1️⃣ Clone the Repository
git clone <your-repo-url>
cd EC-OnlineBookShop
2️⃣ Configure Database

Update application.properties (or active profile file):

spring.datasource.url=jdbc:mysql://localhost:3306/bookshopdb
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

For MongoDB:

spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=bookshopdb
3️⃣ Run the Application

Using Maven:

mvn spring-boot:run

OR

mvn clean package
java -jar target/EC-OnlineBookShop-0.0.1-SNAPSHOT.jar
🌐 API Access

Base URL:

http://localhost:8080

Use Postman to test REST APIs.

🧪 Testing

JUnit test cases are available in:

src/test/java/com/nit/main/

Includes:

BooksControllerTest

CustomerControllerTest

FileControllerTest

UserRegistrationTest

🎯 Skills Demonstrated

Spring Boot REST API Development

Layered Architecture Implementation

DTO Pattern Usage

Global Exception Handling

MongoDB + MySQL Integration

Excel File Upload Processing

Multi-Profile Configuration

Unit Testing with JUnit

Clean Project Structuring
