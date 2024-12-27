# Spring Micrometer Prometheus Grafana Tutorial

This project demonstrates how to integrate Spring Boot with Micrometer, Prometheus, and Grafana for monitoring and observability.

## Prerequisites

- Java 21
- Docker
- Docker Compose
- Maven

## Setup

1. **Build the project:**
   ```sh
   cd spring-micrometer-prometheus-graphana
   mvn clean install
   ```

3. **Build and run Docker containers:**
   ```sh
   docker-compose up -d
   ```

## Accessing the Services

- **Spring Boot Application:** `http://localhost:8080`
- **Prometheus:** `http://localhost:9090`
- **Grafana:** `http://localhost:3000` (Default credentials: `admin/admin`)

## Endpoints

- **Hello Endpoint:** `http://localhost:8080/hello`
- **Prometheus Metrics:** `http://localhost:8080/actuator/prometheus`

## Configuration

Prometheus configuration is located at `config/prometheus/prometheus.yml`.

## Monitoring

1. **Prometheus:** Access Prometheus at `http://localhost:9090` to view metrics.
2. **Grafana:** Access Grafana at `http://localhost:3000` to create dashboards and visualize metrics.

## License

This project is licensed under the MIT License.

