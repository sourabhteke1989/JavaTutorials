FROM openjdk:21-jdk

# Set the working directory inside the container
WORKDIR /app

# Define the JAR_FILE argument
ARG JAR_FILE

# Copy the JAR file from the build stage into the container
COPY ${JAR_FILE} /app/app.jar

# Expose the port that your Spring Boot app will run on
EXPOSE 8080

# Define the command to run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]