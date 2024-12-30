# Spring Security JWT Tutorial

This project demonstrates how to implement JWT (JSON Web Token) authentication in a Spring Boot application. It includes the following components:

## Components

### JwtTokenProvider

The `JwtTokenProvider` is responsible for generating and validating JWT tokens. It uses a secret key to sign the tokens and provides methods to extract claims and validate tokens.

### SecurityConfig

The `SecurityConfig` class configures the security settings for the application. It defines the security filter chain, authentication manager, and user details service. It also sets up the `AuthFilter` to intercept requests and validate JWT tokens.

The `securityFilterChain` method in `SecurityConfig` class configures the following request matchers:
- `/actuator/**`: Permits all requests.
- `/login-failure`: Permits all requests.
- `/admin/**`: Requires the user to have the `ADMIN` role.
- Any other request: Requires authentication.

It also configures `HttpSecurity#formLogin` to handle the `/login` endpoint:
- On successful login, it forwards the request to `/generate-token`.
- On login failure, it forwards the request to `/login-failure`.

### AuthFilter

The `AuthFilter` is a custom filter that extends `OncePerRequestFilter`. It extracts the JWT token from the request header, validates it, and sets the authentication in the security context if the token is valid.

### AuthController

The `AuthController` handles authentication and user registration requests. It provides endpoints for generating tokens and registering new users. Upon successful login, it generates a JWT token and returns it in the response.

## Endpoints

- `POST /login`: Authenticates the user and generates a JWT token for the authenticated user. (Handled by `HttpSecurity#formLogin` in `SecurityConfig`)
- `POST /admin/register-user`: Registers a new user with the specified roles and permissions. (Only user's having "ADMIN" role will be able to access this end point)

## Postman Collection

A Postman collection is included in the project to help you test the API endpoints. You can find the collection file at `postman/spring-security-jwt-tutorial.postman_collection.json`.

### Using the Collection

The collection includes the following requests:
- `login`: Authenticates the user and generates a JWT token.
- `Authenticated hello`: Sends a GET request to a protected endpoint using the JWT token.
- `Register new admin user`: Registers a new admin user with the specified roles.
- `Register new user`: Registers a new user with the specified roles.

The `login` request automatically saves the JWT token to a collection variable named `token`. This token is then used in the `Authenticated hello`, `Register new admin user`, and `Register new user` requests.

## Usage

1. Clone the repository.
2. Configure the application properties as needed.
3. Run the application.
4. Use the `/login` endpoint to authenticate and receive a JWT token.
5. Use the `/admin/register-user` endpoint to register new users. (Only user's having "ADMIN" role will be able to access this end point)
6. Use the `/hello` endpoint to check if the JWT token generated is authenticated.

## Example

### Login Request

To authenticate, send a POST request with form data containing the `username` and `password` fields.

```json
{
    "username": "user",
    "password": "password"
}
```

Example using `curl`:

```sh
curl -X POST http://localhost:8080/login -d "username=user&password=password"
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
  "permissions": ["READ_PRIVILEGES"]  // Optional
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

