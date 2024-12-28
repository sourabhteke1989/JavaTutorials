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

## Implementation Details

### SampleController

The `SampleController` class exposes a `/hello` endpoint and tracks metrics using Micrometer.

- **Counter:** Tracks the number of times the `/hello` endpoint is accessed.
- **Timer:** Measures the time taken to process requests to the `/hello` endpoint.

## Configuration

Prometheus configuration is located at `config/prometheus/prometheus.yml`.

### Distribution Metrics

The application is configured to collect distribution metrics for HTTP server requests with the following buckets:

- 10ms
- 25ms
- 50ms
- 100ms
- 200ms
- 500ms
- 1s
- 2s
- 5s
- 10s

These buckets help in understanding the latency distribution of the requests.

### Application Properties

The following properties are added to `application.properties` to expose Prometheus metrics:

```properties
# Expose Prometheus metrics endpoint
management.endpoints.web.exposure.include=*
# Show detailed health information
management.endpoint.health.show-details=always
# Add application name as a tag to all metrics
management.metrics.tags.application=${spring.application.name}
# Enable Prometheus endpoint
management.endpoint.prometheus.enabled=true
# Enable Prometheus metrics export
management.prometheus.metrics.export.enabled=true
# Define minimum expected value for HTTP server requests
management.metrics.distribution.minimum-expected-value.http.server.requests=10ms
# Define maximum expected value for HTTP server requests
management.metrics.distribution.maximum-expected-value.http.server.requests=10s
# Define Service Level Objectives (SLO) for HTTP server requests
management.metrics.distribution.slo.http.server.requests=10ms,25ms,50ms,100ms,200ms,500ms,1s,2s,5s,10s
```

## Monitoring

1. **Prometheus:** Access Prometheus at `http://localhost:9090` to view metrics.
2. **Grafana:** Access Grafana at `http://localhost:3000` to create dashboards and visualize metrics.

### Prometheus Queries

To check specific metrics in Prometheus, you can use the following queries:

- **HTTP Server Requests:** `http_server_requests_seconds_bucket{uri="/hello"}`
- **Hello Requests Timer:** `hello_requests_timer_seconds_bucket{le="1.0"}`
- **Hello Requests Total:** `hello_requests_total{job="spring-micrometer-prometheus-grafana"}`

## License


