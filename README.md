# E-Commerce Book Store API

This project is a REST API for an e-commerce application that manages the sale of books. The API provides functionality for user authentication, book browsing, shopping cart management, and order processing. The application uses JWT (JSON Web Token) for security and maintains cart data using session management.

## Features

- **User Authentication**: Users can register, log in, and manage their profile.
- **Role-Based Access**: Admins can manage all users and orders, while regular users can only manage their own profile, cart, and orders.
- **Book Browsing**: Users can browse available books and view details.
- **Shopping Cart**: Users can add books to a cart stored in the session and manage cart items.
- **Order Processing**: Users can place orders based on items in their cart.
- **JWT Authentication**: Secures all endpoints except public endpoints like book browsing.
- **MySQL Database**: Uses Spring Data JPA for persistence with MySQL.

## Technologies Used

- **Spring Boot**: REST API framework.
- **Spring Security with JWT**: For authentication and authorization.
- **Spring Data JPA**: For data persistence with MySQL.
- **Session Management**: Used for storing cart and cart items.
- **DTO (Data Transfer Objects)**: For transferring data between client and server.

## API Endpoints

### Authentication

- `POST /api/auth/login`: Log in to get a JWT token.
- `GET /api/auth/clear-session`: Clear the current user session (cart included).

### User Endpoints

- `GET /api/users/{id}`: Retrieve user details by ID (only for the owner or admin).
- `GET /api/users`: Retrieve all users (admin access only).

### Book Endpoints

- `GET /api/books`: Retrieve all books.
- `GET /api/books/{id}`: Retrieve a book by its ID.

### Order Endpoints

- `GET /api/orders/{id}`: Retrieve an order by its ID (admin can view all, users only their own).
- `GET /api/orders`: Retrieve all orders (admin access only).

### Cart Endpoints

- `GET /api/cart`: Retrieve the current user's cart from the session.

## Database Entities

1. **User**: Stores user information and their roles.
2. **Role**: Defines roles (e.g., `ROLE_USER`, `ROLE_ADMIN`).
3. **Book**: Represents a book available in the store.
4. **Order**: Stores user orders.
5. **OrderItem**: Represents items within an order.
6. **Cart**: Represents the user's shopping cart (stored in the session).
7. **CartItem**: Represents individual items in the cart.

## Project Structure

```plaintext
src
 ├── main
 │   ├── java
 │   │   └── com.plavsic.ecommerce
 │   │       ├── controller        # REST Controllers
 │   │       ├── model             # Entities (User, Book, Order, etc.)
 │   │       ├── repository        # Repositories (DAO Layer)
 │   │       ├── service           # Business Logic (Service Layer)
 │   │       ├── dto               # Data Transfer Objects
 │   │       ├── generic           # Structure for Generic Classes and Interfaces
 │   │       └── security          # Security and JWT Configuration
 │   └── resources
 │       └── application.yaml # MySQL configuration and other settings
```

## Running the Application

1. Clone the repository:

   ```bash
   git clone https://github.com/Plavsic01/E-Commerce-REST-API.git
   ```

2. Configure MySQL database in `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Build and run the project:

   ```bash
   ./mvnw spring-boot:run
   ```

4. The application will start on `http://localhost:8080`.

## Usage

1. **Login** to get a JWT token:

   ```bash
   POST /api/auth/login
   ```

   Use the token in the `Authorization` header for all secured endpoints:

   ```plaintext
   Authorization: Bearer <jwt-token>
   ```

2. **Browse books** without authentication:

   ```bash
   GET /api/books
   ```

3. **Manage the cart** (authenticated users only):

   ```bash
   GET /api/cart
   ```

4. **Place an order** based on items in the cart:

   ```bash
   POST /api/orders
   ```

## Security

- The API uses **JWT** for authentication and authorization.
- **Admin** users can manage books, orders, and users.
- **Regular** users can manage their own profile, view books, manage their cart, and place orders.
