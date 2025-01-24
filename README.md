# User Management API with Java Spring Boot

This is a simple User Management API built using **Java Spring Boot** version **3.4.1**. The application provides basic functionality for managing users, including features like user creation, retrieval, and role assignment. The API is designed for simplicity and is intended for learning and prototype purposes.

The backend is powered by a **PostgreSQL database** for persistent data storage, making it suitable for production environments. The API is documented with **Swagger** to provide an interactive interface for users and developers.

### **New Feature: JWT Authentication**
This API now implements **JWT Authentication** to secure the endpoints. To access protected routes, users must authenticate via the login endpoint, and provide the generated JWT token in the request header for authorization. JWT tokens are required for the following endpoints:
- **GET /api/users** — Retrieve all users
- **GET /api/users/{id}** — Retrieve a specific user by ID

### Features

- **Create Users**: Add new users with specified usernames, passwords, emails, and roles.
- **Retrieve Users**: Get a list of all users or retrieve user details by ID. This now requires **JWT authentication**.
- **Swagger Integration**: Interactive API documentation to explore and test API endpoints.
- **Role Management**: Users are assigned roles such as `ADMIN`, `USER`, or `MODERATOR`.
- **JWT Authentication**: Added **JWT-based authentication** to secure API endpoints, including token generation and validation for accessing protected resources.

## Technologies Used

- **Java 17** (LTS)
- **Spring Boot 3.4.1**: A framework for building Java-based web applications.
- **Spring Data JPA**: Simplifies database interactions and abstracts CRUD operations.
- **PostgreSQL**: A powerful, open-source relational database system.
- **Swagger (OpenAPI)**: For interactive API documentation and testing.
- **Maven**: For project management and dependency management.
- **JWT (JSON Web Token)**: For secure authentication and token-based access to the API.

## Setup and Installation

### 1. Clone the Project

Clone the repository to your local machine using your Git client.

### 2. Set Up PostgreSQL Database

Set up a PostgreSQL database and create the following user:

- **Database**: `demo_db`
- **User**: `demo_user`
- **Password**: `password123`

### 3. Configure the Database Connection

Update the **application.yml** to connect to your PostgreSQL database with the appropriate credentials:

- **spring.datasource.url**: `jdbc:postgresql://localhost:5432/demo_db`
- **spring.datasource.username**: `demo_user`
- **spring.datasource.password**: `password123`

### 4. JWT Token Utilization

Implement the JWT token creation and validation methods in **JwtTokenUtil** to generate and verify tokens for authentication.

### 5. Create Authentication Endpoint

Set up the **AuthController** to handle login requests and return a JWT token upon successful authentication.

### 6. Configure Security

Configure **Spring Security** to enforce JWT-based authentication, where users must provide a valid JWT token to access protected routes such as **/api/users**.

### 7. Build and Run the Application

Build the project and run the application using your IDE or Maven commands.

### 8. Access the API

Once the application is running, you can access the following:

- **Swagger UI**: Open your browser and visit **http://localhost:8080/swagger-ui/index.html** to view and interact with the API documentation.
- **Login to Swagger**: Use the login credentials to obtain a JWT token and pass it in the Authorization header for all protected routes.
  - **Username**: `admin`
  - **Password**: `admin123`
  
- **API Endpoints**: You can test all available endpoints through Swagger, or use any HTTP client like Postman or CURL to interact with the API.

### Example Endpoints:

- **POST** `/api/auth/login` — Login to obtain a JWT token
- **POST** `/api/users` — Create a new user (requires JWT token)
- **GET** `/api/users` — Retrieve all users (requires JWT token)
- **GET** `/api/users/{id}` — Retrieve a specific user by ID (requires JWT token)
- **PUT** `/api/users/{id}` — Update user details (requires JWT token)
- **DELETE** `/api/users/{id}` — Delete a user (requires JWT token)

## API Documentation

The API is documented using **Swagger**. You can access the interactive API documentation at:

[Swagger UI](http://localhost:8080/swagger-ui/index.html)

## Database

The application uses **PostgreSQL** as the database. The database connection is configured to use the `demo_db` database, with the username `demo_user` and password `password123`.

Make sure your PostgreSQL service is running and the database is accessible before starting the application.

## Security Considerations

- **Password Handling**: In this prototype, passwords are stored as plain text. For a production-ready application, it is strongly recommended to hash passwords before storing them in the database (using algorithms like **BCrypt**).
- **Authentication & Authorization**: The application now includes **JWT-based authentication** for accessing protected endpoints. Tokens are issued upon successful login and must be included in the request headers for secure access.

## Contributions

Feel free to fork the repository and submit pull requests if you'd like to contribute. If you encounter any issues or bugs, please open an issue on GitHub.

## License

This project is licensed under the **MIT License**.
