# User Management API with Java Spring Boot

This is a simple User Management API built using **Java Spring Boot** version **3.4.1**. The application provides basic functionality for managing users, including features like user creation, retrieval, and role assignment. The API is designed for simplicity and is intended for learning and prototype purposes.

The backend is powered by a **PostgreSQL database** for persistent data storage, making it suitable for production environments. The API is documented with **Swagger** to provide an interactive interface for users and developers.

## Features

- **Create Users**: Add new users with specified usernames, passwords, emails, and roles.
- **Retrieve Users**: Get a list of all users or retrieve user details by ID.
- **Swagger Integration**: Interactive API documentation to explore and test API endpoints.
- **Role Management**: Users are assigned roles such as `ADMIN`, `USER`, or `MODERATOR`.

## Technologies Used

- **Java 17** (LTS)
- **Spring Boot 3.4.1**: A framework for building Java-based web applications.
- **Spring Data JPA**: Simplifies database interactions and abstracts CRUD operations.
- **PostgreSQL**: A powerful, open-source relational database system.
- **Swagger (OpenAPI)**: For interactive API documentation and testing.
- **Maven**: For project management and dependency management.

## Prerequisites

Before running the project, make sure you have the following installed:

- **Java 17+**
- **Maven** (for dependency management and building the project)
- **PostgreSQL** (installed and running on your local machine or accessible remotely)

## Setup and Installation

### 1. Download the Project

Download or unzip the project files from the repository. You can either:

- Download the ZIP archive from GitHub and extract it to your local machine, or
- Use the GitHub interface to directly download the files.

### 2. Set Up PostgreSQL Database

Before running the application, make sure PostgreSQL is installed and running. Then, create the `demo_db` database and a user `demo_user` with the password `password123`.

#### Steps to create the database and user:

1. Open PostgreSQL and log in as the `postgres` user:
   **`sudo -u postgres psql`**

2. Create the `demo_db` database:
   **`CREATE DATABASE demo_db;`**

3. Create the user `demo_user` with the password `password123`:
   **`CREATE USER demo_user WITH PASSWORD 'password123';`**

4. Grant the user access to the `demo_db` database:
   **`GRANT ALL PRIVILEGES ON DATABASE demo_db TO demo_user;`**

5. Exit `psql`:
   **`\q`**

### 3. Configure Database Connection

In your **`application.properties`** (or **`application.yml`** if you use YAML), update the database connection settings to use PostgreSQL:

- **`spring.datasource.url=jdbc:postgresql://localhost:5432/demo_db`**
- **`spring.datasource.username=demo_user`**
- **`spring.datasource.password=password123`**
- **`spring.datasource.driver-class-name=org.postgresql.Driver`**

For JPA configuration:

- **`spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect`**
- **`spring.jpa.hibernate.ddl-auto=update`**
- **`spring.jpa.show-sql=true`**
- **`spring.jpa.properties.hibernate.format_sql=true`**

Optional connection pool configuration:

- **`spring.datasource.hikari.maximum-pool-size=10`**
- **`spring.datasource.hikari.connection-timeout=30000`**

### 4. Build the Project

Navigate to the project directory and build the project using Maven:

**`mvn clean install`**

### 5. Run the Application

Once the project is built, you can run it using Maven:

**`mvn spring-boot:run`**

Alternatively, if you have the project set up in your IDE (e.g., IntelliJ IDEA, Eclipse), you can run the main class `com.example.demo.DemoApplication` directly.

### 6. Access the API

The application will run on **http://localhost:8080** by default.

- **Swagger UI**: Open your browser and visit **http://localhost:8080/swagger-ui/index.html** to view and interact with the API documentation.
- **Login to Swagger**: You can log into Swagger UI with the following credentials:
  - **Username**: `admin`
  - **Password**: `admin123`
  
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

The application uses **PostgreSQL** as the database. The database connection is configured to use the `demo_db` database, with the username `demo_user` and password `password123`.

Make sure your PostgreSQL service is running and the database is accessible before starting the application.

## Security Considerations

- **Password Handling**: In this prototype, passwords are stored as plain text. For a production-ready application, it is strongly recommended to hash passwords before storing them in the database (using algorithms like BCrypt).
- **Authentication & Authorization**: Currently, the application does not include any form of authentication or authorization. You may consider adding Spring Security for securing the API in future updates.

## Contributions

Feel free to fork the repository and submit pull requests if you'd like to contribute. If you encounter any issues or bugs, please open an issue on GitHub.

## License

This project is licensed under the MIT License.
