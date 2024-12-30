# Spring Security JWT Tutorial

This project demonstrates how to implement JWT (JSON Web Token) authentication in a Spring Boot application. It includes the following components:

## Components

### JwtTokenProvider

The `JwtTokenProvider` is responsible for generating and validating JWT tokens. It uses a secret key to sign the tokens and provides methods to extract claims and validate tokens.

### SecurityConfig

The `SecurityConfig` class configures the security settings for the application. It defines the security filter chain, authentication manager, and user details service. It also sets up the `AuthFilter` to intercept requests and validate JWT tokens.

The `securityFilterChain` method in `SecurityConfig` class configures the following request matchers:
- `POST /login`: Permits all requests.
- `/actuator/**`: Permits all requests.
- `/admin/**`: Requires the user to have the `ADMIN` role.
- Any other request: Requires authentication.

### AuthFilter

The `AuthFilter` is a custom filter that extends `OncePerRequestFilter`. It extracts the JWT token from the request header, validates it, and sets the authentication in the security context if the token is valid.

### AuthController

The `AuthController` handles authentication and user registration requests. It provides endpoints for logging in and registering new users. Upon successful login, it generates a JWT token and returns it in the response.

## Endpoints

- `POST /login`: Authenticates the user and returns a JWT token.
- `POST /admin/register-user`: Registers a new user with the specified roles and permissions.

## Postman Collection

A Postman collection is included in the project to help you test the API endpoints. You can find the collection file at `postman/spring-security-jwt-tutorial.postman_collection.json`.

### Using the Collection

The collection includes the following requests:
- `login`: Authenticates the user and retrieves a JWT token.
- `Authenticated hello`: Sends a GET request to a protected endpoint using the JWT token.
- `Register new admin user`: Registers a new admin user with the specified roles.

Make sure to update the `token` variable in the collection with the JWT token obtained from the `login` request.

## Usage

1. Clone the repository.
2. Configure the application properties as needed.
3. Run the application.
4. Use the `/login` endpoint to authenticate and receive a JWT token.
5. Use the `/admin/register-user` endpoint to register new users.

## Example

### Login Request

```json
{
  "username": "user",
  "password": "password"
}
```

### Login Response

```json
{
  "username": "user",
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### Register User Request

```json
{
  "username": "newuser",
  "password": "newpassword",
  "roles": ["USER"],
  "permissions": ["READ_PRIVILEGES"]  //Optional
}
```

### Register User Response

```json
{
  "message": "User created successfully"
}
```

## Conclusion

This project provides a basic implementation of JWT authentication in a Spring Boot application. It can be extended and customized to fit specific requirements.

