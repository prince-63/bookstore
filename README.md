### 📚 Bookstore Backend - Spring Boot Project

A **production-grade Bookstore backend** built with **Java, Spring Boot, MySQL**, and **JWT-based Security**. It allows users to register, log in securely, and browse books by category or author. Admins can manage books, authors, and categories. Users can also download book files (PDFs) directly.

This project is built to simulate a real-world online bookstore backend and is structured to demonstrate production-quality development skills.

<video autoplay>
  <source src="https://res.cloudinary.com/duhumawiq/video/upload/v1755350581/bookstore_mrrjeb.mp4" type="video/mp4">
</video>

#### 🔧 Tech Stack

* Java 21, Spring Boot 3
* Spring Security + JWT
* Spring Data JPA (Hibernate)
* MySQL, Maven
* Cloudinary (file/image uploads)
* Swagger (OpenAPI docs)

#### 🔐 Auth & Access

* JWT-secured endpoints
* Role-based authorization (USER, ADMIN)

#### 📑 API Documentation

* Visit Swagger UI for all API documentation and testing: `http://localhost:8080/swagger-ui.html`
* Automatically generated using springdoc-openapi

#### 📦 Features

* 👥 User & Admin registration/login
* 📚 CRUD for Book, Author, and Category
* ☁️ Upload and download book files and cover images

#### 🧾 Modules

* `User`, `Address`, `Book`, `Author`, `Category`
* Organized by `controllers`, `services`, `repositories`, `dto`, `models`, `config`

#### 🚀 Run Locally

```bash
mvn clean install
mvn spring-boot:run
```
