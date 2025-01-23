# User Management API with Java Spring Boot

This is a simple User Management API built using **Java Spring Boot** version **3.4.1**. The application provides basic functionality for managing users, including features like user creation, retrieval, and role assignment. The API is designed for simplicity and is intended for learning and prototype purposes.

The backend is powered by an **in-memory H2 database** for lightweight, temporary data storage, making it ideal for testing and development purposes. The API is documented with **Swagger** to provide an interactive interface for users and developers.

## Features

- **Create Users**: Add new users with specified usernames, passwords, emails, and roles.
- **Retrieve Users**: Get a list of all users or retrieve user details by ID.
- **Swagger Integration**: Interactive API documentation to explore and test API endpoints.
- **Role Management**: Users are assigned roles such as `ADMIN`, `USER`, or `MODERATOR`.

## Technologies Used

- **Java 17** (LTS)
- **Spring Boot 3.4.1**: A framework for building Java-based web applications.
- **Spring Data JPA**: Simplifies database interactions and abstracts CRUD operations.
- **H2 Database**: An in-memory database for lightweight, temporary data storage.
- **Swagger (OpenAPI)**: For interactive API documentation and testing.
- **Maven**: For project management and dependency management.

## Prerequisites

Before running the project, make sure you have the following installed:

- **Java 17+**
- **Maven** (for dependency management and building the project)

## Setup and Installation

### 1. Download the Project

Download or unzip the project files from the repository. You can either:

- Download the ZIP archive from GitHub and extract it to your local machine, or
- Use the GitHub interface to directly download the files.

### 2. Build the Project

Navigate to the project directory and build the project using Maven:

**mvn clean install**

### 3. Run the Application

Once the project is built, you can run it using Maven:

**mvn spring-boot:run**

Alternatively, if you have the project set up in your IDE (e.g., IntelliJ IDEA, Eclipse), you can run the main class `com.example.demo.DemoApplication` directly.

### 4. Access the API

The application will run on **http://localhost:8080** by default.

- **Swagger UI**: Open your browser and visit **http://localhost:8080/swagger-ui/index.html** to view and interact with the API documentation.
- **API Endpoints**: You can test all available endpoints through Swagger, or use any HTTP client like Postman or CURL to interact with the API.

### Example Endpoints:

- **POST** `/api/users` — Create a new user
- **GET** `/api/users` — Retrieve all users
- **GET** `/api/users/{id}` — Retrieve a specific user by ID
- **PUT** `/api/users/{id}` — Update user details
- **DELETE** `/api/users/{id}` — Delete a user

## API Documentation

The API is documented using **Swagger**. You can access the interactive API documentation at:

[Swagger UI](http://localhost:8080/swagger-ui/index.html)

## Database

The application uses an **H2 in-memory database** by default, which means that the database will be wiped out when the application is restarted. This is ideal for testing, learning, and prototyping but not recommended for production.

## Security Considerations

- **Password Handling**: In this prototype, passwords are stored as plain text. For a production-ready application, it is strongly recommended to hash passwords before storing them in the database (using algorithms like BCrypt).
- **Authentication & Authorization**: Currently, the application does not include any form of authentication or authorization. You may consider adding Spring Security for securing the API in future updates.

## Contributions

Feel free to fork the repository and submit pull requests if you'd like to contribute. If you encounter any issues or bugs, please open an issue on GitHub.

## License

This project is licensed under the MIT License.
